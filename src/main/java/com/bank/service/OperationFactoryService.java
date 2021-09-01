package com.bank.service;

import com.bank.dto.OperationMetaAggregator;
import com.bank.operation.Operation;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class OperationFactoryService {

    private String PACKAGE = "com.bank";
    private Map<String, Class<? extends Operation>> operationMapping;

    public Optional<Operation> createOperation(OperationMetaAggregator aggregator) {
        try {
            Class<?extends Operation> operationClass = operationMapping.get(aggregator.getOperationName());
            Operation operation = operationClass.getConstructor().newInstance(aggregator);
            return Optional.of(operation);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    @PostConstruct
    public void initOperationFactoryService(){
        Reflections reflections = new Reflections(PACKAGE);
        Set<Class<? extends Operation>> allClasses = reflections.getSubTypesOf(Operation.class);
        for (Class<? extends Operation> aClass : allClasses){
            operationMapping.put(aClass.getName(), aClass);
        }
    }
}
