package com.mangement.dto;

import com.mangement.domain.TaskPriority;
import com.mangement.domain.TaskStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Calendar;

public record Task(
        String description,
        @NotNull(message = "task status is mandatory")
        TaskStatus taskStatus,
        @NotNull(message = "task priority is mandatory")
        TaskPriority taskPriority,
        Calendar issuedAt,
        @NotNull(message = "deadline is mandatory")
        Calendar deadline
) {
}
