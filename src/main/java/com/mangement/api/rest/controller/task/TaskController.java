package com.mangement.api.rest.controller.task;

import com.mangement.api.dto.Employee;
import com.mangement.api.dto.PersonalInformation;
import com.mangement.api.dto.Task;
import com.mangement.exception.EmployeeNotFoundException;
import com.mangement.exception.ProjectNotFoundException;
import com.mangement.exception.TaskCreationException;
import com.mangement.exception.TaskNotFoundException;
import com.mangement.service.task.TaskServiceImpl;
import com.mangement.store.domain.employee.Division;
import com.mangement.store.domain.employee.Role;
import com.mangement.store.domain.task.TaskPriority;
import com.mangement.store.domain.task.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private static final String GET_ALL_MATCHING_TASKS_OF_PROJECT = "projects/{project_id}/tasks";
    private static final String GET_ALL_TASKS_OF_PROJECT = "projects/{project_id}/tasks";
    private static final String GET_TASK_BY_ID = "projects/tasks/{task_id}";
    private static final String POST_NEW_TASK_TO_PROJECT = "projects/{project_id}/tasks";
    private static final String PUT_TASK_OF_PROJECT = "projects/{project_id}/tasks/{task_id}";
    private static final String DELETE_TASK_FROM_PROJECT = "projects/{project_id}/tasks/{task_id}";

    @Autowired
    @Qualifier("taskServiceImpl")
    private TaskServiceImpl taskService;

    @PostMapping(POST_NEW_TASK_TO_PROJECT)
    public ResponseEntity<Task> create(@NotNull @PathVariable("project_id") Long projectId, @Valid @RequestBody Task task) throws TaskCreationException {
        logger.info("started to handle POST request on end point " + POST_NEW_TASK_TO_PROJECT);
        taskService.createInProject(projectId, task);
        return ResponseEntity.created(URI.create(POST_NEW_TASK_TO_PROJECT)).build();
    }

    @GetMapping(GET_ALL_MATCHING_TASKS_OF_PROJECT)
    public ResponseEntity<Page<Task>> findAllMatchingInProjectAndSort (@NotNull @PathVariable("project_id") Long projectId,
                                                                       @RequestParam(name = "description", required = false) String description,
                                                                       @RequestParam(name = "task_status", required = false) TaskStatus taskStatus,
                                                                       @RequestParam(name = "task_priority", required = false) TaskPriority taskPriority,
                                                                       @RequestParam(name = "issuedAt", required = false) LocalDateTime issuedAt,
                                                                       @RequestParam(name = "deadline", required = false) LocalDateTime deadline,
                                                                       @RequestParam(name = "direction", required = false) String direction,
                                                                       @RequestParam(name = "sort_param", required = false) Set<String> sortParams)
            throws TaskNotFoundException, ProjectNotFoundException{
        logger.info("started to handle GET request on end point " + GET_ALL_MATCHING_TASKS_OF_PROJECT);
        Task task = new Task(description, taskStatus, taskPriority, issuedAt, deadline);
        return ResponseEntity.ok(taskService.findAllMatchingInProjectAndSort(projectId ,task, direction, sortParams));
    }

    @GetMapping(GET_ALL_TASKS_OF_PROJECT)
    public ResponseEntity<Page<Task>> findAllEmployeesOnProject(@NotNull @PathVariable("project_id") Long projectId,
                                                                @RequestParam(name = "direction", required = false) String direction,
                                                                @RequestParam(name = "sort_param", required = false) Set<String> sortParams)
            throws TaskNotFoundException {

        logger.info("started to handle GET request on end point " + GET_ALL_TASKS_OF_PROJECT);
        return ResponseEntity.ok(taskService.findAllInProject(projectId, direction, sortParams));
    }

    @PutMapping(PUT_TASK_OF_PROJECT)
    public ResponseEntity<Task> update(@NotNull @PathVariable("project_id") Long projectId,
                                       @NotNull @PathVariable("project_id") Long taskId,
                                       @Valid @RequestBody Task source) throws TaskNotFoundException {
        logger.info("started to handle PUT request on end point " + PUT_TASK_OF_PROJECT);
        return ResponseEntity.ok(taskService.update(projectId, taskId, source));
    }

    @DeleteMapping(DELETE_TASK_FROM_PROJECT)
    public ResponseEntity<Employee> deleteById(@NotNull @PathVariable("project_id") Long projectId,
                                               @NotNull @PathVariable("project_id") Long taskId) {
        logger.info("started to handle DELETE request on end point " + DELETE_TASK_FROM_PROJECT);
        taskService.deleteById(projectId, taskId);
        return ResponseEntity.noContent().build();
    }
}
