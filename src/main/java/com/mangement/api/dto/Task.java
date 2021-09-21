package com.mangement.api.dto;

import com.mangement.store.domain.task.TaskPriority;
import com.mangement.store.domain.task.TaskStatus;

import java.time.LocalDateTime;

public record Task(
        String description,
        TaskStatus taskStatus,
        TaskPriority taskPriority,
        LocalDateTime issuedAt,
        LocalDateTime deadline
) {
}
