package com.mangement.jms;

import com.mangement.dto.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMessageSender {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeMessageSender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${employee.jms.destination}")
    private String jmsQueue;

    public void sendMessage(Employee employee){
        jmsTemplate.convertAndSend(jmsQueue, employee);
        logger.info("producer> message was sent to destination" + jmsQueue);
    }
}
