package com.mangement.controller;

import com.mangement.dto.Employee;
import com.mangement.dto.Project;
import com.mangement.dto.Task;
import com.mangement.exception.ProjectNotFoundException;
import com.mangement.exception.TaskCreationException;
import com.mangement.exception.TaskNotFoundException;
import com.mangement.service.ProjectService;
import com.mangement.domain.ProjectStatus;
import com.mangement.domain.TaskPriority;
import com.mangement.domain.TaskStatus;
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
import java.util.Map;
import java.util.Set;

@RestController
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private static final String GET_ALL_MATCHING_PROJECTS = "/projects";
    private static final String GET_PROJECT_BY_ID = "/projects/{project_id}";
    private static final String PUT_PROJECT = "/projects/{project_id}";
    private static final String POST_NEW_PROJECT = "/projects";

    private static final String GET_ALL_MATCHING_TASKS_OF_PROJECT = "projects/{project_id}/tasks";
    private static final String GET_ALL_TASKS_OF_PROJECT = "projects/{project_id}/tasks";
    private static final String POST_NEW_TASK_TO_PROJECT = "projects/{project_id}/tasks";
    private static final String PUT_TASK_OF_PROJECT = "projects/{project_id}/tasks/{task_id}";
    private static final String DELETE_TASK_FROM_PROJECT = "projects/{project_id}/tasks/{task_id}";

    @Autowired
    @Qualifier("projectServiceImpl")
    private ProjectService projectService;

    @PostMapping(POST_NEW_PROJECT)
    public ResponseEntity<Project> createProject(@Valid @RequestParam Project project){
        logger.info("started to handle POST request on end point " + POST_NEW_PROJECT);
        projectService.create(project);

        return ResponseEntity.created(URI.create(POST_NEW_PROJECT)).build();
    }

    @PostMapping(POST_NEW_TASK_TO_PROJECT)
    public ResponseEntity<Task> create (@NotNull @PathVariable("project_id") Long projectId,
                                        @Valid @RequestBody Task task)
                                        throws TaskCreationException, ProjectNotFoundException {
        logger.info("started to handle POST request on end point " + POST_NEW_TASK_TO_PROJECT);
        projectService.addTask(projectId, task);
        return ResponseEntity.created(URI.create(POST_NEW_TASK_TO_PROJECT)).build();
    }

    @GetMapping(GET_PROJECT_BY_ID)
    public ResponseEntity<Project> findProjectById (@NotNull @PathVariable("project_id") Long projectId)
                                                    throws ProjectNotFoundException {
        logger.info("started to handle GET request on end point " + GET_PROJECT_BY_ID);
        return ResponseEntity.ok(projectService.findById(projectId));
    }

    @GetMapping(GET_ALL_MATCHING_PROJECTS)
    public ResponseEntity<Page<Project>> findAllMatchingProjectsAndSort(@RequestParam(required = false, name = "name") String name,
                                                                        @RequestParam(required = false, name = "description") String description,
                                                                        @RequestParam(required = false, name = "tasks") Set<Task> projectTasks,
                                                                        @RequestParam(required = false, name = "status") ProjectStatus projectStatus,
                                                                        @RequestParam(required = false, name = "employees") Set<Employee> projectEmployees,
                                                                        @RequestParam(required = false, name = "direction") String direction,
                                                                        @RequestParam(required = false, name = "sort_param") Set<String> sortParams)
                                                                        throws ProjectNotFoundException {
        logger.info("started to handle GET request on end point " + GET_ALL_MATCHING_PROJECTS);
        Project project = new Project(name, description, projectTasks, projectEmployees, projectStatus);
        return ResponseEntity.ok(projectService.findAllMatchingAndSort(project, direction, sortParams));
    }

    @GetMapping(GET_ALL_MATCHING_TASKS_OF_PROJECT)
    public ResponseEntity<Page<Task>> findAllMatchingTasksInProjectAndSort (@NotNull @PathVariable("project_id") Long projectId,
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
        return ResponseEntity.ok(projectService.findAllMatchingTasksInProjectAndSort(projectId ,task, direction, sortParams));
    }

    @GetMapping(GET_ALL_TASKS_OF_PROJECT)
    public ResponseEntity<Page<Task>> findAllTasksInProject(@NotNull @PathVariable("project_id") Long projectId,
                                                            @RequestParam(name = "direction", required = false) String direction,
                                                            @RequestParam(name = "sort_param", required = false) Set<String> sortParams)
                                                            throws TaskNotFoundException, ProjectNotFoundException {

        logger.info("started to handle GET request on end point " + GET_ALL_TASKS_OF_PROJECT);
        return ResponseEntity.ok(projectService.findAllTasksInProject(projectId, direction, sortParams));
    }

    @PutMapping(PUT_TASK_OF_PROJECT)
    public ResponseEntity<Task> updateTask (@NotNull @PathVariable("project_id") Long projectId,
                                            @NotNull @PathVariable("project_id") Long taskId,
                                            @Valid @RequestBody Task source)
                                            throws TaskNotFoundException, ProjectNotFoundException {
        logger.info("started to handle PUT request on end point " + PUT_TASK_OF_PROJECT);
        return ResponseEntity.ok(projectService.updateTask(projectId, taskId, source));
    }

    @PutMapping(PUT_PROJECT)
    public ResponseEntity<Project> update(@NotNull @PathVariable("project_id") Long projectId,
                                          @Valid @RequestBody Project source)
                                          throws ProjectNotFoundException {
        logger.info("started to handle PUT request on end point " + PUT_PROJECT);
        return ResponseEntity.ok(projectService.update(projectId, source));
    }

    @DeleteMapping(DELETE_TASK_FROM_PROJECT)
    public ResponseEntity<Employee> deleteTaskById (@NotNull @PathVariable("project_id") Long projectId,
                                                    @NotNull @PathVariable("project_id") Long taskId)
                                                    throws ProjectNotFoundException, TaskNotFoundException {
        logger.info("started to handle DELETE request on end point " + DELETE_TASK_FROM_PROJECT);
        projectService.deleteTaskById(projectId, taskId);
        return ResponseEntity.noContent().build();
    }



}
