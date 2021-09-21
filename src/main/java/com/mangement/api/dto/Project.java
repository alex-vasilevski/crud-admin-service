package com.mangement.api.dto;

import com.mangement.store.domain.project.ProjectStatus;

import javax.validation.constraints.NotNull;
import java.util.Set;

public record Project(
        String name,
        String description,
        Set<Task> projectTasks,
        Set<Employee> employees,
        @NotNull(message = "status is required")
        ProjectStatus projectStatus
) {
        Project(){
                this(null, null, null, null, null);
        }
}
