package com.bank.repository;

import com.bank.domain.operators.AdministratorEntity;
import org.springframework.data.repository.CrudRepository;

public interface AdministratorRepository extends CrudRepository<AdministratorEntity, Long> {
}
