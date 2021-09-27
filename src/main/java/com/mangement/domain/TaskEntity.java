package com.mangement.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "task")
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
    @JoinColumn(name = "id", table = "project" )
    @OneToOne(fetch = FetchType.LAZY)
    private ProjectEntity project;

    public TaskEntity(String description, TaskStatus taskStatus, TaskPriority taskPriority, LocalDateTime issuedAt, LocalDateTime deadline) {
        this.description = description;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
        this.issuedAt = issuedAt;
        this.deadline = deadline;
    }
}
