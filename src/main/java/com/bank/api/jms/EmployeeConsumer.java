package com.bank.api.jms;

import com.bank.api.dto.v2.Employee;
import com.bank.service.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class EmployeeConsumer {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeConsumer.class);

    @Autowired
    private EmployeeServiceImpl service;

    @JmsListener(destination =  "${employeeEntity.jms.destination}", containerFactory = "jmsFactory")
    public void receiveMessage(@Valid Employee employee){
        logger.info("consumer> " + employee);
        service.create(employee);
    }
}
