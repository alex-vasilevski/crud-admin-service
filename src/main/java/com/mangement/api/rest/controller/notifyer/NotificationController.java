package com.mangement.api.rest.controller.notifyer;

import com.mangement.api.dto.Project;
import com.mangement.exception.ProjectNotFoundException;
import com.mangement.service.notifyer.DeadlineNotificationService;
import com.mangement.store.domain.task.TaskPriority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
public class NotificationController {

    private static final String POST_NEW_NOTIFICATION_LEVEL = "notifications/";
    private static final String POST_NEW_NOTIFIABLE_PROJECT = "notifications/{project_id}";
    private static final String POST_DAYS_BEFORE_DEAD_LINE = "notifications/d";
    private static final String GET_ALL_NOTIFIABLE_PROJECTS = "notifications/projects";
    private static final String GET_NOTIFIABLE_PROJECT = "notifications/{project_id}";

    @Autowired
    private DeadlineNotificationService deadlineNotificationService;

    @PostMapping(POST_NEW_NOTIFICATION_LEVEL)
    public ResponseEntity<TaskPriority> changeNotificationLevel(@NotNull @RequestBody TaskPriority newNotificationLevel){
        deadlineNotificationService.setNotificationLevel(newNotificationLevel);
        return ResponseEntity.ok().build();
    }

    @PostMapping(POST_NEW_NOTIFIABLE_PROJECT)
    public ResponseEntity<Project> setProjectNotifiable(@NotNull @PathVariable("project_id") Long projectId) throws ProjectNotFoundException {
        Project project = deadlineNotificationService.setNotifiable(projectId);
        return ResponseEntity.ok(project);
    }

    @PostMapping(POST_DAYS_BEFORE_DEAD_LINE)
    public void setDaysBeforeDeadLine(@NotNull @RequestParam(name = "days", required = true) Long days){
        deadlineNotificationService.setDaysBeforeDeadline(days);
    }
}
