package com.mangement.transformers;

import com.mangement.dto.Employee;
import com.mangement.domain.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeTransformer implements Transformer<EmployeeEntity, Employee> {
    @Override
    public Employee toDto(EmployeeEntity employeeEntity) {
        return null;
    }

    @Override
    public EmployeeEntity toEntity(Employee employee) {
       return null;
    }
}
