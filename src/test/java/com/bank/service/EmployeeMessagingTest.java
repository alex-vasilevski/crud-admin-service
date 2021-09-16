package com.bank.service;

import com.bank.api.controllers.jms.EmployeeMessageController;
import com.bank.api.dto.Employee;
import com.bank.store.domain.EmployeeEntity;
import com.bank.store.domain.Role;
import com.bank.store.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeMessagingTest {

    @Autowired
    private EmployeeMessageController messageController;
    @Autowired
    private EmployeeProducerService producerService;
    @Autowired
    private EmployeeRepository employeeRepository;


    private static final Long ID = 1L;
    private static final String NAME = "Alex";
    private static final String LAST_NAME = "Vasilevski";
    private static final LocalDate BIRTH_DAY = LocalDate.of(1999, Month.AUGUST, 20);
    private static final Integer AGE = 22;
    private static final Double SALARY = 750.5D;
    private static final Role ROLE = Role.CLERK;

    @Test
    public void shouldSendMessageAndReceiveMessageAndStoreItToDB(){
        producerService.sendMessage(defaultDtoEmployee());
        EmployeeEntity expected = defaultEntityEmployee();
        Optional<EmployeeEntity> actual = employeeRepository.findById(ID);
        assertEquals(expected, actual.get());
    }

    public void shouldAbortNotValidMessage(){

    }

    private static Employee defaultDtoEmployee(){
        return new Employee(NAME, LAST_NAME, BIRTH_DAY, AGE, SALARY, ROLE);
    }

    private static EmployeeEntity defaultEntityEmployee(){
        EmployeeEntity employeeEntity = new EmployeeEntity(NAME, LAST_NAME, BIRTH_DAY, AGE, SALARY, ROLE);
        employeeEntity.setId(ID);
        return employeeEntity;
    }
}
