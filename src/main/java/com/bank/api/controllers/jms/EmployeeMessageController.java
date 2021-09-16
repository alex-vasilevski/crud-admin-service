package com.bank.api.controllers.jms;

import com.bank.api.dto.Employee;
import com.bank.service.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Controller
public class EmployeeMessageController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeMessageController.class);

    @Autowired
    private EmployeeServiceImpl service;

    @JmsListener(destination =  "${jms.queue}")
    public void receiveMessage(@Valid Employee employee){
        logger.info("received employee: " + employee.toString());
        service.create(employee);
    }
}
