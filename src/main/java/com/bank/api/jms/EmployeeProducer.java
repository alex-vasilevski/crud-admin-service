package com.bank.api.jms;

import com.bank.api.dto.v2.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmployeeProducer {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeConsumer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${employeeEntity.jms.destination}")
    private String jmsQueue;

    public void sendMessage(Employee employee){
        jmsTemplate.convertAndSend(jmsQueue, employee);
        logger.info("producer> message was sent");
    }
}
