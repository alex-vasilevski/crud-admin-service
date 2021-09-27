package com.mangement.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<TaskEntity> projectTasks;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<EmployeeEntity> employees;
    private ProjectStatus projectStatus;

    public ProjectEntity(String name, String description, Set<TaskEntity> projectTasks, Set<EmployeeEntity> employees, ProjectStatus projectStatus) {
        this.name = name;
        this.description = description;
        this.projectTasks = projectTasks;
        this.employees = employees;
        this.projectStatus = projectStatus;
    }
}
