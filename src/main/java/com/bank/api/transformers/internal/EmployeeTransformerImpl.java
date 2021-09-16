package com.bank.api.transformers.internal;

import com.bank.api.dto.v1.Employee;
import com.bank.api.transformers.spi.Transformer;
import com.bank.store.domain.EmployeeEntity;
import org.springframework.stereotype.Component;

@Deprecated
@Component
public class EmployeeTransformerImpl implements Transformer<EmployeeEntity, Employee> {

    public Employee toDto(EmployeeEntity entity){
        return new Employee(
                entity.getName(),
                entity.getLastName(),
                entity.getBirthDay(),
                entity.getAge(),
                entity.getSalary(),
                entity.getDivision(),
                entity.getRole());
    }

    public EmployeeEntity toEntity(Employee dto){
        return new EmployeeEntity(
                dto.getName(),
                dto.getLastName(),
                dto.getBirthDay(),
                dto.getAge(),
                dto.getSalary(),
                dto.getDivision(),
                dto.getRole());
    }

}
