package com.bank.service;

import com.bank.api.jms.EmployeeConsumer;
import com.bank.api.dto.v2.Employee;
import com.bank.api.jms.EmployeeProducer;
import com.bank.store.domain.Division;
import com.bank.store.domain.EmployeeEntity;
import com.bank.store.domain.Role;
import com.bank.store.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class EmployeeMessagingTest {

    private static final Long ID = 1L;
    private static final String NAME = "Alex";
    private static final String LAST_NAME = "Vasilevski";
    private static final Calendar BIRTH_DAY = new GregorianCalendar(1999, 8, 20);
    private static final Calendar START_DAY = new GregorianCalendar(2021, 8, 23);
    private static final Integer AGE = 22;
    private static final Double SALARY = 750.5D;
    private static final Role ROLE = Role.CLERK;
    private static final Division DIVISION = Division.JAVA;

    @Autowired
    private EmployeeConsumer employeeConsumer;
    @Autowired
    private EmployeeProducer employeeProducer;
    @Autowired
    private EmployeeRepository employeeRepository;


    public void shouldAbortNotValidMessage(){

    }

    private static Employee defaultDtoEmployee(){
        return new Employee(NAME, LAST_NAME, BIRTH_DAY, AGE, START_DAY, SALARY, DIVISION, ROLE);
    }

    private static EmployeeEntity defaultEntityEmployee(){
        EmployeeEntity employeeEntity =
                new EmployeeEntity(NAME, LAST_NAME, BIRTH_DAY, AGE, START_DAY, SALARY, DIVISION, ROLE);
        employeeEntity.setId(ID);
        return employeeEntity;
    }
}
