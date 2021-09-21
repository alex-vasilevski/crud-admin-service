package com.mangement.api.transformers.internal;

import com.mangement.api.dto.Task;
import com.mangement.api.transformers.spi.Transformer;
import com.mangement.store.domain.task.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskTransformer implements Transformer<TaskEntity, Task> {
    @Override
    public Task toDto(TaskEntity taskEntity) {
        return null;
    }

    @Override
    public TaskEntity toEntity(Task task) {
        return null;
    }
}
