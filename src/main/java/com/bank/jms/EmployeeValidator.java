package com.bank.jms;

import com.bank.store.domain.EmployeeEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EmployeeValidator  implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(EmployeeEntity.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeEntity employeeEntity = (EmployeeEntity) target;
        if(employeeEntity == null){
            errors.reject(null, "employee cannot be null");
        }
    }
}
