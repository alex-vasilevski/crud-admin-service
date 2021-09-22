package com.mangement.service.notifyer;

import com.mangement.api.dto.Notification;
import com.mangement.api.dto.Project;
import com.mangement.api.dto.Task;
import com.mangement.exception.ProjectNotFoundException;
import com.mangement.service.project.ProjectService;
import com.mangement.store.domain.task.TaskPriority;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeadlineNotificationService {

    @Autowired
    @Qualifier("projectServiceImpl")
    private ProjectService projectService;

    @Autowired
    private DeadLineNotificationsProducer producer;

    @Getter
    @Setter
    private TaskPriority notificationLevel;
    @Getter
    @Setter
    private Long daysBeforeDeadline;


    private Map<Long, Project> projectIdMapping = new HashMap<>();
    private Map<Project, Set<Task>> notifiableTasks = new HashMap<>();

    public void doNotification(){
        updateNotifiable();
        List<Notification> notification = producer.produce(notifiableTasks);
    }

    public Project setNotifiable(Long projectId) throws ProjectNotFoundException {
        Project project = projectService.findById(projectId);
        projectIdMapping.put(projectId, project);
        return project;
    }

    private void updateNotifiable(){
        for (Long id : projectIdMapping.keySet()){
            Project project = projectIdMapping.get(id);

            Set<Task> filtered = project.projectTasks()
                    .stream()
                    .filter(task -> task.taskPriority().equals(notificationLevel))
                    .filter(task -> task.deadline().minusDays(daysBeforeDeadline).equals(LocalDateTime.now()))
                    .collect(Collectors.toSet());

            notifiableTasks.put(project, filtered);
        }
    }


}
