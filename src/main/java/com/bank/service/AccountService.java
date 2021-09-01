package com.bank.service;

import com.bank.domain.AccountEntity;
import com.bank.domain.operators.AdministratorEntity;
import com.bank.dto.Account;
import com.bank.dto.Administrator;
import com.bank.repository.AccountRepository;
import com.bank.transformer.AccountTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements CrudService<Account, Long>{

    @Autowired
    private AccountRepository repository;
    @Autowired
    private AccountTransformer transformer;

    @Override
    public void create(Account account) {
        repository.save(transformer.toEntity(account));
    }

    @Override
    public Optional<Account> findById(Long id) {
        return repository.findById(id).map(transformer::toDto);
    }

    @Override
    public List<Account> findAll() {
        List<AccountEntity> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list.stream().map(transformer::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<Account> update(Long id, Account sourceDto) {
        Optional<AccountEntity> optional = repository.findById(id);
        AccountEntity source = transformer.toEntity(sourceDto);
        optional.ifPresent(target -> update(source, target));
        return optional.map(transformer::toDto);
    }

    private AccountEntity update(AccountEntity source, AccountEntity target){
        target.setCards(source.getCards());
        target.setOwner(source.getOwner());
        target.setTotal(source.getTotal());
        target.setStatus(source.getStatus());
        return target;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
