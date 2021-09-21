package com.mangement.service.task;

import com.mangement.api.dto.Employee;
import com.mangement.api.dto.Project;
import com.mangement.api.dto.Task;
import com.mangement.api.transformers.spi.Transformer;
import com.mangement.exception.EmployeeNotFoundException;
import com.mangement.exception.ProjectNotFoundException;
import com.mangement.exception.TaskCreationException;
import com.mangement.exception.TaskNotFoundException;
import com.mangement.service.project.ProjectService;
import com.mangement.service.util.PageableProducer;
import com.mangement.store.domain.employee.EmployeeEntity;
import com.mangement.store.domain.task.TaskEntity;
import com.mangement.store.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class TaskServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    @Qualifier("projectServiceImpl")
    private ProjectService projectService;

    @Autowired
    @Qualifier("taskTransformer")
    private Transformer<TaskEntity, Task> transformer;

    @Autowired
    private TaskRepository repository;

    @Autowired
    private PageableProducer pageableProducer;

    public void createInProject(Long projectId, Task task) throws TaskCreationException {
        try {
            Project project = projectService.findById(projectId);
            project.addTask(task);
            projectService.update(projectId, project);
        }
        catch (ProjectNotFoundException e) {
            throw new TaskCreationException("cannot create task because project with id " + projectId + " does not exist");
        }
    }

    public Task findById(Long taskId) throws TaskNotFoundException {
        logger.info("trying to find task with id " + taskId);

        TaskEntity entity = repository
                .findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("employee with id " + taskId + " not found"));

        return transformer.toDto(entity);
    }

    public Page<Task> findAllMatchingInProjectAndSort(Long projectId, Task searchCriteria, String direction, Set<String> sortParams) throws TaskNotFoundException{

    }

    public Page<Task> findAllInProject(Long projectId, String direction, Set<String> sortParams) throws TaskNotFoundException {

    }

    public Task update(Long projectId, Long taskId, Task task) throws TaskNotFoundException{

    }

    public void deleteById(Long projectId, Long taskId){

    }
}
