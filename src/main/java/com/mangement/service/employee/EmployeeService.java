package com.mangement.service.employee;

import com.mangement.api.dto.Employee;
import com.mangement.exception.EmployeeNotFoundException;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface EmployeeService {
    void create(Employee employee);

    Employee findById(Long id) throws EmployeeNotFoundException;

    Page<Employee> findAllMatchingAndSort(Employee searchCriteria, String direction, Set<String> sortParams) throws EmployeeNotFoundException;

    Page<Employee> findAllOnProject(Long projectId, String direction, Set<String> sortParams) throws EmployeeNotFoundException;

    Employee update(Long id, Employee dto) throws EmployeeNotFoundException;

    void deleteById(Long id);
}
