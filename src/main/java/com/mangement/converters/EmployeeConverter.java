package com.mangement.converters;

import com.mangement.domain.PersonalInformationEntity;
import com.mangement.dto.Employee;
import com.mangement.domain.EmployeeEntity;
import com.mangement.dto.PersonalInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("employeeConverter")
public class EmployeeConverter extends AbstractGenericConverter<EmployeeEntity, Employee> {

    @Autowired
    @Qualifier("personalInformationConverter")
    private Converter personalInfoConverter;

    @Override
    protected Employee toDto(EmployeeEntity employeeEntity) {
        PersonalInformation information = personalInfoConverter.convert(employeeEntity.getPersonalInformation(), PersonalInformation.class);
        return new Employee(employeeEntity.getSalary(), employeeEntity.getDivision(), employeeEntity.getRole(), information);
    }

    @Override
    protected EmployeeEntity toEntity(Employee employee) {
        PersonalInformationEntity information = personalInfoConverter.convert(employee.personalInformation(), PersonalInformationEntity.class);
        return new EmployeeEntity(employee.salary(), employee.division(), employee.role(), information);
    }
}
