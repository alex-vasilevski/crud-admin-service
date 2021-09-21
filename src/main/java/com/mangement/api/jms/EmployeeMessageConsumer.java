package com.mangement.api.jms;

import com.mangement.api.dto.Employee;
import com.mangement.service.employee.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class EmployeeMessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeMessageConsumer.class);

    @Autowired
    private EmployeeServiceImpl service;

    @JmsListener(destination = "${employee.jms.destination}", containerFactory = "jmsFactory")
    public void receiveMessage(@Valid Employee employee){
        logger.info("consumer> " + employee);
        service.create(employee);
    }
}
