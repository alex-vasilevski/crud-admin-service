package com.bank.service;

import com.bank.api.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProducerService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${jms.queue}")
    private String jmsQueue;

    public void sendMessage(Employee employee){
        jmsTemplate.convertAndSend(jmsQueue, employee);
    }
}
