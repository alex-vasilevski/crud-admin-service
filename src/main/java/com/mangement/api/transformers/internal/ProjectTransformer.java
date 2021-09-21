package com.mangement.api.transformers.internal;

import com.mangement.api.dto.Project;
import com.mangement.api.transformers.spi.Transformer;
import com.mangement.store.domain.project.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectTransformer implements Transformer<ProjectEntity, Project> {
    @Override
    public Project toDto(ProjectEntity projectEntity) {
        return null;
    }

    @Override
    public ProjectEntity toEntity(Project project) {
        return null;
    }
}
