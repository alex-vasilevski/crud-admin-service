package com.bank.api.transformers.internal;

import com.bank.api.dto.Employee;
import com.bank.api.transformers.spi.EmployeeTransformer;
import com.bank.api.transformers.spi.Transformer;
import com.bank.store.domain.EmployeeEntity;
import com.bank.store.domain.Role;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EmployeeTransformerImpl implements EmployeeTransformer {

    public Employee toDto(EmployeeEntity entity){
        return new Employee(
                entity.getName(),
                entity.getLastName(),
                entity.getBirthDay(),
                entity.getAge(),
                entity.getSalary(),
                entity.getRole());
    }

    public EmployeeEntity toEntity(Employee dto){
        return new EmployeeEntity(
                dto.getName(),
                dto.getLastName(),
                dto.getBirthDay(),
                dto.getAge(),
                dto.getSalary(),
                dto.getRole());
    }

}
