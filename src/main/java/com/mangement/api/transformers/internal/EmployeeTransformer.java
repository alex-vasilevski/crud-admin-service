package com.mangement.api.transformers.internal;

import com.mangement.api.dto.Employee;
import com.mangement.api.transformers.spi.Transformer;
import com.mangement.store.domain.employee.EmployeeEntity;
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
