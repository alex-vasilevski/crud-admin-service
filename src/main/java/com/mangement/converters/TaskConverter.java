package com.mangement.converters;

import com.mangement.dto.Task;
import com.mangement.domain.TaskEntity;
import org.springframework.stereotype.Component;

@Component("taskConverter")
public class TaskConverter extends AbstractGenericConverter<TaskEntity, Task> {
    @Override
    protected Task toDto(TaskEntity taskEntity) {
        return new Task(taskEntity.getDescription(), taskEntity.getTaskStatus(), taskEntity.getTaskPriority(), taskEntity.getIssuedAt(), taskEntity.getDeadline());
    }

    @Override
    protected TaskEntity toEntity(Task task) {
        return new TaskEntity(task.description(), task.taskStatus(), task.taskPriority(), task.issuedAt(), task.deadline());
    }
}
