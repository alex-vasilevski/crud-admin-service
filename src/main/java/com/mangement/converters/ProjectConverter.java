package com.mangement.converters;

import com.mangement.domain.EmployeeEntity;
import com.mangement.domain.TaskEntity;
import com.mangement.dto.Employee;
import com.mangement.dto.Project;
import com.mangement.domain.ProjectEntity;
import com.mangement.dto.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("projectConverter")
public class ProjectConverter extends AbstractGenericConverter<ProjectEntity, Project> {

    @Autowired
    @Qualifier("employeeConverter")
    private Converter employeeConverter;

    @Autowired
    @Qualifier("taskConverter")
    private Converter taskConverter;

    @Override
    protected Project toDto(ProjectEntity projectEntity) {
        Set<Task> tasks = projectEntity.getProjectTasks().stream()
                .map(entity -> taskConverter.convert(entity, Task.class))
                .collect(Collectors.toSet());

        Set<Employee> employees = projectEntity.getEmployees().stream()
                .map(entity -> taskConverter.convert(entity, Employee.class))
                .collect(Collectors.toSet());
        return new Project(projectEntity.getName(), projectEntity.getDescription(), tasks, employees, projectEntity.getProjectStatus());
    }

    @Override
    protected ProjectEntity toEntity(Project project) {
        Set<TaskEntity> taskEntities = project.projectTasks().stream()
                .map(task -> taskConverter.convert(task, TaskEntity.class))
                .collect(Collectors.toSet());

        Set<EmployeeEntity> employeeEntities = project.employees().stream()
                .map(employee -> taskConverter.convert(employee, EmployeeEntity.class))
                .collect(Collectors.toSet());
        return new ProjectEntity(project.name(), project.description(), taskEntities, employeeEntities, project.projectStatus());
    }
}
