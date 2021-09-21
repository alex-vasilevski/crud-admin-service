package com.mangement.api.rest.controller.project;

import com.mangement.api.dto.Employee;
import com.mangement.api.dto.Project;
import com.mangement.api.dto.Task;
import com.mangement.exception.ProjectNotFoundException;
import com.mangement.service.project.ProjectService;
import com.mangement.service.project.ProjectServiceImpl;
import com.mangement.store.domain.project.ProjectStatus;
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
import java.util.List;
import java.util.Set;

@RestController
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private static final String GET_ALL_MATCHING_PROJECTS = "/projects";
    private static final String GET_PROJECT_BY_ID = "/project/{project_id}";
    private static final String PUT_PROJECT = "/projects/{project_id}";
    private static final String POST_NEW_PROJECT = "/projects";

    @Autowired
    @Qualifier("projectServiceImpl")
    private ProjectService projectService;

    @PostMapping(POST_NEW_PROJECT)
    public ResponseEntity<Project> create(@Valid @RequestParam Project project){
        logger.info("started to handle POST request on end point " + POST_NEW_PROJECT);
        projectService.create(project);
        return ResponseEntity.created(URI.create(POST_NEW_PROJECT)).build();
    }

    @GetMapping(GET_PROJECT_BY_ID)
    public ResponseEntity<Project> findById(@NotNull @PathVariable("project_id") Long projectId) throws ProjectNotFoundException {
        logger.info("started to handle GET request on end point " + GET_PROJECT_BY_ID);
        return ResponseEntity.ok(projectService.findById(projectId));
    }

    @GetMapping(GET_ALL_MATCHING_PROJECTS)
    public ResponseEntity<Page<Project>> findAllMatchingAndSort (@RequestParam(required = false, name = "name") String name,
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



    @PutMapping(PUT_PROJECT)
    public ResponseEntity<Project> update(@NotNull @PathVariable("project_id") Long projectId, @Valid @RequestBody Project source) throws ProjectNotFoundException {
        logger.info("started to handle PUT request on end point " + PUT_PROJECT);
        return ResponseEntity.ok(projectService.update(projectId, source));
    }





}
