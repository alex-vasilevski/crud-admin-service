package com.mangement.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;

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
    @Temporal(TemporalType.DATE)
    private Calendar issuedAt;
    @Temporal(TemporalType.DATE)
    private Calendar deadline;
    @JoinColumn(name = "id", table = "project" )
    @OneToOne(fetch = FetchType.LAZY)
    private ProjectEntity project;

    public TaskEntity(String description, TaskStatus taskStatus, TaskPriority taskPriority, Calendar issuedAt, Calendar deadline) {
        this.description = description;
        this.taskStatus = taskStatus;
        this.taskPriority = taskPriority;
        this.issuedAt = issuedAt;
        this.deadline = deadline;
    }
}
