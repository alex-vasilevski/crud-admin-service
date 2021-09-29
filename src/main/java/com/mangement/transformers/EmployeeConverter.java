package com.mangement.transformers;

import com.mangement.dto.Employee;
import com.mangement.domain.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component("employeeConverter")
public class EmployeeConverter implements Converter<EmployeeEntity, Employee> {
    @Override
    public Employee toDto(EmployeeEntity employeeEntity) {
        return null;
    }

    @Override
    public EmployeeEntity toEntity(Employee employee) {
       return null;
    }
}
