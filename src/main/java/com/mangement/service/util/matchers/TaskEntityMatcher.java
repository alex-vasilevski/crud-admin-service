package com.mangement.service.util.matchers;

import com.mangement.store.domain.task.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskEntityMatcher implements Matcher<TaskEntity> {
    @Override
    public Boolean isMatching(TaskEntity a, TaskEntity b) {
        boolean deadline = a.getDeadline().equals(b.getDeadline());
        boolean description = a.getDescription().contains(b.getDescription());
        boolean taskPriority = a.getTaskPriority().equals(b.getTaskPriority());
        boolean issuedAt = a.getIssuedAt().equals(b.getIssuedAt());
        boolean taskStatus = a.getTaskStatus().equals(b.getTaskStatus());

        return deadline | description | taskStatus | taskPriority | issuedAt;
    }
}
