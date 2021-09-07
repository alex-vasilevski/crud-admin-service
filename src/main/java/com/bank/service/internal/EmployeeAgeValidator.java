package com.bank.service.internal;

import com.bank.domain.Employee;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

public class EmployeeAgeValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Employee.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Employee employee = (Employee) object;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "age.empty");
        if(employee.getAge() < 18){
            errors.rejectValue("age", "too young");
        } else if(employee.getAge() > 60){
            errors.rejectValue("age", "too old");
        }
    }
}
