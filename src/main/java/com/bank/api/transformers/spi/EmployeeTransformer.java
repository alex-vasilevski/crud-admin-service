package com.bank.api.transformers.spi;

import com.bank.api.dto.Employee;
import com.bank.store.domain.EmployeeEntity;

public interface EmployeeTransformer extends Transformer<EmployeeEntity, Employee>{
}
