package com.mangement.transformers;

import com.mangement.dto.Project;
import com.mangement.domain.ProjectEntity;
import org.springframework.stereotype.Component;

@Component("projectConverter")
public class ProjectConverter implements Converter<ProjectEntity, Project> {
    @Override
    public Project toDto(ProjectEntity projectEntity) {
        return null;
    }

    @Override
    public ProjectEntity toEntity(Project project) {
        return null;
    }
}
