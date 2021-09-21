package com.mangement.store.domain.task;

import com.mangement.store.domain.project.ProjectEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    @Enumerated(EnumType.STRING)
    private TaskPriority taskPriority;
    private LocalDateTime issuedAt;
    private LocalDateTime deadline;
    @JoinColumn(table = "project")
    @OneToMany(fetch = FetchType.LAZY, targetEntity = ProjectEntity.class)
    private ProjectEntity project;

    public TaskEntity(String description, TaskStatus taskStatus, TaskPriority taskPriority, LocalDateTime issuedAt, LocalDateTime deadline) {
        this.description = description;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
        this.issuedAt = issuedAt;
        this.deadline = deadline;
    }
}
