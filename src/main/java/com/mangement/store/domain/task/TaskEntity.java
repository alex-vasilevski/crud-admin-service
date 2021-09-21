package com.mangement.store.domain.task;

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
    private LocalDateTime deadLine;

    public TaskEntity(String description, TaskStatus taskStatus, TaskPriority taskPriority, LocalDateTime issuedAt, LocalDateTime deadLine) {
        this.description = description;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
        this.issuedAt = issuedAt;
        this.deadLine = deadLine;
    }
}
