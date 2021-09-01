package com.bank.service;

import com.bank.dto.OperationMetaAggregator;
import com.bank.operation.Operation;
import com.bank.policy.operation.OperationStatus;
import com.bank.repository.OperationMetaAggregatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class OperationService {

    @Autowired
    private Set<CrudService> services;
    @Autowired
    private OperationFactoryService factoryService;
    @Autowired
    private Set<Validator> validators;
    @Autowired
    private OperationMetaAggregatorRepository repository;

    private Map<String, CrudService> serviceMapping;



    public List<OperationMetaAggregator> findAllMatching(OperationMetaAggregator operationDetails){

    }

    public OperationMetaAggregator findById(Long id){

    }

    @Transactional
    public OperationStatus perform(OperationMetaAggregator operationDetails){
        Optional<Operation> optional= factoryService.createOperation(operationDetails);
        if(optional.isEmpty()){
            return OperationStatus.DECLINED;
        }
        Operation operation = optional.get();
        for(Validator validator : validators){
            if(!validator.validate(operation, operationDetails)){
                return OperationStatus.DECLINED;
            }
        }
        return operation.perform(serviceMapping).getOperationStatus();
    }

    @PostConstruct
    public void initOperationService(){
        services.forEach(service -> serviceMapping.put(service.getClass().getSimpleName(), service));
    }

}
