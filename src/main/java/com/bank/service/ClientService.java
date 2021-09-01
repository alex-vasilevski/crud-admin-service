package com.bank.service;

import com.bank.domain.CardEntity;
import com.bank.domain.operators.ClientEntity;
import com.bank.dto.Client;
import com.bank.repository.ClientRepository;
import com.bank.transformer.ClientTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService implements CrudService<Client, Long>{

    @Autowired
    private ClientTransformer transformer;
    @Autowired
    private ClientRepository repository;

    @Override
    public void create(Client client) {
        repository.save(transformer.toEntity(client));
    }

    @Override
    public Optional<Client> findById(Long id) {
        return repository.findById(id).map(transformer::toDto);
    }

    @Override
    public Iterable<Client> findAll() {
        List<ClientEntity> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list.stream().map(transformer::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<Client> update(Long id, Client sourceDto) {
        Optional<ClientEntity> optional = repository.findById(id);
        ClientEntity source = transformer.toEntity(sourceDto);
        optional.ifPresent(target -> update(source, target));
        return optional.map(transformer::toDto);
    }

    private ClientEntity update(ClientEntity source, ClientEntity target){
        target.setAccountEntities(target.getAccountEntities());
        target.setLastName(source.getLastName());
        target.setName(source.getName());
        target.setRights(source.getRights());
        return target;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
