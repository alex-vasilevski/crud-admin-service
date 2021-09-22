package com.mangement.service.notifyer;

import com.mangement.api.dto.Employee;
import com.mangement.api.dto.Notification;
import com.mangement.api.dto.Project;
import com.mangement.api.dto.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DeadLineNotificationsProducer {

    public List<Notification> produce(Map<Project, Set<Task>> notifiableTasks) {
        List<Notification> notifications = new ArrayList<>();
        for (Project project : notifiableTasks.keySet()){
            notifications.addAll(produceNotificationsForProject(project, notifiableTasks.get(project)));
        }

        return notifications;
    }

    private List<Notification>produceNotificationsForProject(Project project, Set<Task> notifiableTasks){
        Stream<String> emails = project.employees().stream().map(Employee::email);
        return emails
                .map(email -> produceNotification(email, project, notifiableTasks))
                .collect(Collectors.toList());

    }

    private Notification produceNotification(String email, Project project, Set<Task> notifiableTasks){
        String greeting = "Hi, deadline is soon! on project " + project.name() +
                "there are some unfinished tasks: ";
        int counter = 1;
        StringBuilder taskCount = new StringBuilder();
        for (Task task : notifiableTasks){
            taskCount.append(counter + ". " + task.description() +
                    ", issued " + task.issuedAt() +
                    ", status " + task.taskStatus() +
                    ", priority " + task.taskPriority() + "\n");
        }

        String notificationText = greeting.concat(taskCount.toString());

        return new Notification(email, notificationText);
    }

}
