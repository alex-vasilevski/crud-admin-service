package com.mangement.service.project;

import com.mangement.api.dto.Project;
import com.mangement.exception.ProjectNotFoundException;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface ProjectService {
    void create(Project project);

    Project findById(Long projectId) throws ProjectNotFoundException;

    Page<Project> findAllMatchingAndSort(Project searchCriteria, String direction, Set<String> sortParams) throws ProjectNotFoundException;

    Project update(Long projectId, Project project) throws ProjectNotFoundException;
}
