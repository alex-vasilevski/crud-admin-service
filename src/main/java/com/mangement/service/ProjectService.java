package com.mangement.service;

import com.mangement.dto.Project;
import com.mangement.dto.Task;
import com.mangement.exception.ProjectNotFoundException;
import com.mangement.exception.TaskNotFoundException;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface ProjectService {
    void create(Project project);

    Project findById(Long projectId) throws ProjectNotFoundException;

    Page<Project> findAllMatchingAndSort(Project searchCriteria, String direction, Set<String> sortParams) throws ProjectNotFoundException;

    Project update(Long projectId, Project project) throws ProjectNotFoundException;

    Page<Task> findAllMatchingTasksInProjectAndSort(Long projectId, Task searchingCriteria, String direction, Set<String> sortParams) throws TaskNotFoundException, ProjectNotFoundException;;

    Page<Task> findAllTasksInProject(Long projectId, String direction, Set<String> sortParams) throws TaskNotFoundException, ProjectNotFoundException;;

    Task updateTask(Long projectId, Long taskId, Task source) throws TaskNotFoundException, ProjectNotFoundException;;

    void deleteTaskById(Long projectId, Long taskId) throws TaskNotFoundException, ProjectNotFoundException;;

    void addTask(Long projectId, Task task) throws ProjectNotFoundException;
}
