package com.bank.api.jms.validator;

import com.bank.api.dto.v2.Employee;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EmployeeValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(Employee.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;
        if(employee == null){
            errors.reject(null, "employee cannot be null");
        }
    }
}
