package com.bank.api.transformers.internal;

import com.bank.api.dto.Employee;
import com.bank.api.transformers.spi.EmployeeTransformer;
import com.bank.api.transformers.spi.Transformer;
import com.bank.store.domain.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeTransformerImpl implements EmployeeTransformer {

    public Employee toDto(EmployeeEntity entity){
        return Employee.builder()
                .age(entity.getAge())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .birthDay(entity.getBirthDay())
                .salary(entity.getSalary())
                .role(entity.getRole())
                .build();
    }

    public EmployeeEntity toEntity(Employee dto){
        return EmployeeEntity.builder()
                .age(dto.getAge())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .birthDay(dto.getBirthDay())
                .salary(dto.getSalary())
                .role(dto.getRole())
                .build();
    }

}
