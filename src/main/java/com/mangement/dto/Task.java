package com.mangement.dto;

import com.mangement.domain.TaskPriority;
import com.mangement.domain.TaskStatus;

import java.time.LocalDateTime;

public record Task(
        String description,
        TaskStatus taskStatus,
        TaskPriority taskPriority,
        LocalDateTime issuedAt,
        LocalDateTime deadline
) {
}
