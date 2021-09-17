package com.bank.api.transformers.internal;

import com.bank.api.dto.v2.Employee;
import com.bank.api.transformers.spi.Transformer;
import com.bank.store.domain.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRecordTransformer implements Transformer<EmployeeEntity, Employee> {
    @Override
    public Employee toDto(EmployeeEntity employeeEntity) {
        return new Employee(employeeEntity.getName(),
                employeeEntity.getLastName(),
                employeeEntity.getBirthDay(),
                employeeEntity.getAge(),
                employeeEntity.getStartDay(),
                employeeEntity.getSalary(),
                employeeEntity.getDivision(),
                employeeEntity.getRole());
    }

    @Override
    public EmployeeEntity toEntity(Employee employee) {
        return new EmployeeEntity(employee.name(),
                employee.lastName(),
                employee.birthDay(),
                employee.age(),
                employee.startDay(),
                employee.salary(),
                employee.division(),
                employee.role());
    }
}
