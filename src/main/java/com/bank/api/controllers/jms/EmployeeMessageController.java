package com.bank.api.controllers.jms;

import com.bank.api.dto.Employee;
import com.bank.service.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class EmployeeMessageController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeMessageController.class);

    @Autowired
    private EmployeeServiceImpl service;

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination =  "${jms.queue}", containerFactory = "myFactory")
    public void receiveMessage(@Valid Employee employee){
        logger.info("received employee: " + employee.toString());
        service.create(employee);
    }
}
