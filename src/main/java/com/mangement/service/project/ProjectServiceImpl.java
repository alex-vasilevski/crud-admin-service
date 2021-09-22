package com.mangement.service.project;

import com.mangement.api.dto.Project;
import com.mangement.api.dto.Task;
import com.mangement.exception.TaskCreationException;
import com.mangement.exception.TaskNotFoundException;
import com.mangement.service.util.PageableProducer;
import com.mangement.api.transformers.spi.Transformer;
import com.mangement.exception.ProjectNotFoundException;
import com.mangement.service.employee.EmployeeServiceImpl;
import com.mangement.service.util.matchers.Matcher;
import com.mangement.store.domain.project.ProjectEntity;
import com.mangement.store.domain.task.TaskEntity;
import com.mangement.store.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.ProviderNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private ProjectRepository repository;

    @Autowired
    @Qualifier("projectTransformer")
    private Transformer<ProjectEntity, Project> projectTransformer;

    @Autowired
    @Qualifier("taskTransformer")
    private Transformer<TaskEntity, Task> taskTransformer;

    @Autowired
    @Qualifier("taskEntityMatcher")
    private Matcher<TaskEntity> taskEntityMatcher;

    @Autowired
    private PageableProducer pageableProducer;

    @Override
    public void create(Project project){
        logger.info("tying to create project: " + project.toString());

        ProjectEntity projectEntity = projectTransformer.toEntity(project);

        repository.save(projectEntity);
    }

    @Override
    public Project findById(Long projectId) throws ProjectNotFoundException {
        logger.info("trying to find project with id " + projectId);

        ProjectEntity entity = repository
                .findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("project with id " + projectId + " not found"));

        return projectTransformer.toDto(entity);
    }

    @Override
    public Page<Project> findAllMatchingAndSort(Project searchCriteria, String direction, Set<String> sortParams) throws ProjectNotFoundException{
        Pageable pageable = pageableProducer.produce(direction, sortParams);
        ProjectEntity criteria = projectTransformer.toEntity(searchCriteria);

        logger.info("trying to find matching projects by following params: " + searchCriteria +
                "; using sorting by params " + Arrays.toString(sortParams.toArray()) +
                ", with direction: " + direction);

        Page<ProjectEntity> searchResults = repository.findAll(Example.of(criteria), pageable);

        if(searchResults.getTotalElements() == 0)
            throw new ProjectNotFoundException("no matching results");

        return searchResults.map(projectTransformer::toDto);
    }

    @Override
    public Project update(Long projectId, Project project) throws ProjectNotFoundException{
        logger.info("trying to update project with id + " + projectId + " with following value: " + project.toString());

        Optional<ProjectEntity> optional = repository.findById(projectId);
        ProjectEntity target = optional.orElseThrow(() -> new ProjectNotFoundException("project with id " + projectId + " not found"));
        ProjectEntity source = projectTransformer.toEntity(project);
        target = update(target, source);
        return projectTransformer.toDto(target);
    }

    private ProjectEntity update(ProjectEntity source, ProjectEntity target){
        target.setDescription(source.getDescription());
        target.setEmployees(source.getEmployees());
        target.setProjectStatus(source.getProjectStatus());
        target.setProjectTasks(source.getProjectTasks());
        return target;
    }

    @Override
    public Page<Task> findAllMatchingTasksInProjectAndSort (Long projectId, Task searchingCriteria, String direction, Set<String> sortParams)
                                                            throws TaskNotFoundException, ProjectNotFoundException {
        logger.info("trying to fetch tasks matching with " + searchingCriteria + "from project with id + " + projectId);

        ProjectEntity projectEntity = repository
                .findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("cannot find tasks: project with id" + projectId + " does not exist"));

        Set<TaskEntity> taskEntities = projectEntity.getProjectTasks();

        TaskEntity criteria = taskTransformer.toEntity(searchingCriteria);
        List<Task> filtered = taskEntities
                .stream()
                .filter(taskEntity -> taskEntityMatcher.isMatching(taskEntity, criteria))
                .map(taskTransformer::toDto)
                .collect(Collectors.toList());

        if (filtered.isEmpty())
            throw new TaskNotFoundException("no matching results");

        Pageable pageable = pageableProducer.produce(direction, sortParams);

        return new PageImpl<>(filtered, pageable, filtered.size());
    }

    @Override
    public Page<Task> findAllTasksInProject(Long projectId, String direction, Set<String> sortParams)
                                            throws TaskNotFoundException, ProjectNotFoundException {
        ProjectEntity projectEntity = repository
                .findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("cannot find tasks: project with id" + projectId + " does not exist"));

        List<Task> tasks = projectEntity.getProjectTasks()
                .stream()
                .map(taskTransformer::toDto)
                .collect(Collectors.toList());

        if (tasks.isEmpty())
            throw new TaskNotFoundException("no matching results");

        Pageable pageable = pageableProducer.produce(direction, sortParams);

        return new PageImpl<>(tasks, pageable, tasks.size());
    }

    @Override
    public Task updateTask(Long projectId, Long taskId, Task source) throws TaskNotFoundException, ProjectNotFoundException {
        ProjectEntity projectEntity = repository
                .findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("cannot find tasks: project with id" + projectId + " does not exist"));

        Set<TaskEntity> projectTasks = projectEntity.getProjectTasks();

        TaskEntity targetEntity = projectTasks
                .stream()
                .filter(entity -> entity.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new TaskRejectedException("no task with id " + taskId + " found"));

        TaskEntity sourceEntity = taskTransformer.toEntity(source);
        targetEntity = updateTaskEntity(targetEntity, sourceEntity);

        return taskTransformer.toDto(targetEntity);
    }

    private TaskEntity updateTaskEntity(TaskEntity target, TaskEntity source){
        target.setDeadline(source.getDeadline());
        target.setDescription(source.getDescription());
        target.setTaskPriority(source.getTaskPriority());
        target.setIssuedAt(source.getIssuedAt());
        target.setTaskStatus(source.getTaskStatus());

        return target;
    }

    @Override
    public void deleteTaskById(Long projectId, Long taskId) throws TaskNotFoundException, ProjectNotFoundException {
        ProjectEntity projectEntity = repository
                .findById(projectId)
                .orElseThrow(() -> new ProviderNotFoundException("cannot find tasks: project with id" + projectId + " does not exist"));

        Set<TaskEntity> projectTasks = projectEntity.getProjectTasks();

        TaskEntity targetEntity = projectTasks
                .stream()
                .filter(entity -> entity.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new TaskRejectedException("no task with id " + taskId + " found"));

        projectTasks.remove(targetEntity);
    }

    @Override
    public void addTask(Long projectId, Task task) throws ProjectNotFoundException {
        ProjectEntity projectEntity = repository
                .findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("cannot find tasks: project with id" + projectId + " does not exist"));

        Set<TaskEntity> projectTasks = projectEntity.getProjectTasks();
        projectTasks.add(taskTransformer.toEntity(task));
    }
}

