package com.bank.jms;

import com.bank.domain.Employee;
import com.bank.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import javax.validation.Valid;

public class EmployeeConsumer {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeConsumer.class);

    @Autowired
    private EmployeeRepository repository;

    @JmsListener(destination = "${employee.jms.destination}", containerFactory = "jmsFactory")
    public void processEmployee(@Valid Employee employee){
        logger.info("consumer: " + employee.toString());
        logger.info("consumer: employee saved " + repository.save(employee));
    }
}
