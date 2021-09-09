package com.bank.jms;

import com.bank.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmployeeProducer {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendTo(String destination, Employee employee){
        jmsTemplate.convertAndSend(destination, employee);
        logger.info("producer: message was sent");
    }
}
