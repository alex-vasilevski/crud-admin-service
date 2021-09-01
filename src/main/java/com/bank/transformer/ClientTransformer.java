package com.bank.transformer;

import com.bank.domain.operators.ClientEntity;
import com.bank.dto.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientTransformer implements Transformer<ClientEntity, Client> {
    @Override
    public Client toDto(ClientEntity clientEntity) {
        return null;
    }

    @Override
    public ClientEntity toEntity(Client client) {
        return null;
    }
}
