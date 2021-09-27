package com.mangement.dto;

import com.mangement.domain.ProjectStatus;

import javax.validation.constraints.NotNull;
import java.util.Set;

public record Project(
        @NotNull
        String name,
        String description,
        Set<Task> projectTasks,
        Set<Employee> employees,
        @NotNull(message = "status is required")
        ProjectStatus projectStatus) {
        Project(){
                this(null, null, null, null, null);
        }

        public void addTask(Task task){
                this.projectTasks.add(task);
        }

        public void removeTask(Task task){
                this.projectTasks.remove(task);
        }
}
