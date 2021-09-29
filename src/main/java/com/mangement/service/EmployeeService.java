package com.mangement.service;

import com.mangement.dto.Employee;
import com.mangement.exception.EmployeeNotFoundException;
import com.mangement.exception.ProjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface EmployeeService {
    void create(Employee employee);

    Employee findById(Long id) throws EmployeeNotFoundException;

    Page<Employee> findAllMatchingAndSort(Employee searchCriteria, Pageable pageable) throws EmployeeNotFoundException;

    Page<Employee> findAllOnProject(Long projectId, Pageable pageable) throws EmployeeNotFoundException, ProjectNotFoundException;

    Employee update(Long id, Employee dto) throws EmployeeNotFoundException;

    void deleteById(Long id);
}
