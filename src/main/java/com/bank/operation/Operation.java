package com.bank.operation;

import com.bank.dto.OperationMetaAggregator;
import com.bank.service.CrudService;

import java.util.Map;

public interface Operation {
    OperationMetaAggregator perform(Map<String, CrudService> crudServiceMapping);
}
