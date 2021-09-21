package com.mangement.service.project;

import com.mangement.api.dto.Project;
import com.mangement.service.util.PageableProducer;
import com.mangement.api.transformers.spi.Transformer;
import com.mangement.exception.ProjectNotFoundException;
import com.mangement.service.employee.EmployeeServiceImpl;
import com.mangement.store.domain.project.ProjectEntity;
import com.mangement.store.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private ProjectRepository repository;

    @Autowired
    @Qualifier("projectTransformer")
    private Transformer<ProjectEntity, Project> transformer;

    @Autowired
    private PageableProducer pageableProducer;

    @Override
    public void create(Project project){
        logger.info("tying to create project: " + project.toString());

        ProjectEntity projectEntity = transformer.toEntity(project);

        repository.save(projectEntity);
    }

    @Override
    public Project findById(Long projectId) throws ProjectNotFoundException {
        logger.info("trying to find project with id " + projectId);

        ProjectEntity entity = repository
                .findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("project with id " + projectId + " not found"));

        return transformer.toDto(entity);
    }

    @Override
    public Page<Project> findAllMatchingAndSort(Project searchCriteria, String direction, Set<String> sortParams) throws ProjectNotFoundException{
        Pageable pageable = pageableProducer.produce(direction, sortParams);
        ProjectEntity criteria = transformer.toEntity(searchCriteria);

        logger.info("trying to find matching projects by following params: " + searchCriteria +
                "; using sorting by params " + Arrays.toString(sortParams.toArray()) +
                ", with direction: " + direction);

        Page<ProjectEntity> searchResults = repository.findAll(Example.of(criteria), pageable);

        if(searchResults.getTotalElements() == 0)
            throw new ProjectNotFoundException("no matching results");

        return searchResults.map(transformer::toDto);
    }

    @Override
    public Project update(Long projectId, Project project) throws ProjectNotFoundException{
        logger.info("trying to update project with id + " + projectId + " with following value: " + project.toString());

        Optional<ProjectEntity> optional = repository.findById(projectId);
        ProjectEntity target = optional.orElseThrow(() -> new ProjectNotFoundException("project with id " + projectId + " not found"));
        ProjectEntity source = transformer.toEntity(project);
        target = update(target, source);
        return transformer.toDto(target);
    }

    private ProjectEntity update(ProjectEntity source, ProjectEntity target){
        target.setDescription(source.getDescription());
        target.setEmployees(source.getEmployees());
        target.setProjectStatus(source.getProjectStatus());
        target.setProjectTasks(source.getProjectTasks());
        return target;
    }

}

