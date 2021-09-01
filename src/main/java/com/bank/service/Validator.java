package com.bank.service;

import com.bank.dto.OperationMetaAggregator;
import com.bank.operation.Operation;

public interface Validator {
    boolean validate(Operation operation, OperationMetaAggregator aggregator);
}
