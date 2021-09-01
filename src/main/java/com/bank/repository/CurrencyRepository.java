package com.bank.repository;

import com.bank.domain.CurrencyEntity;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<CurrencyEntity, Long> {
}
