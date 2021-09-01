package com.bank.service;

import com.bank.dto.Account;
import com.bank.repository.AccountRepository;
import com.bank.transformer.AccountTransformer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AccountService implements CrudService<Account, Long>{

    @Autowired
    private AccountRepository repository;
    @Autowired
    private AccountTransformer transformer;

    @Override
    public void create(Account account) {

    }

    @Override
    public Optional<Account> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public Optional<Account> update(Long aLong, Account source) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
