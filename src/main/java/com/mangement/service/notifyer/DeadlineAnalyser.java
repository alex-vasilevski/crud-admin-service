package com.mangement.service.notifyer;

import com.mangement.store.domain.task.TaskPriority;
import com.mangement.store.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeadlineAnalyser {

    @Autowired
    private TaskRepository repository;


}
