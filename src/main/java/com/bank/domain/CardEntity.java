package com.bank.domain;

import ch.qos.logback.core.net.server.Client;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class CardEntity {
    @Id
    private Long id;
    private Client owner;
    private AccountEntity account;
    private Long number;

    public CardEntity(Client owner, AccountEntity account, Long number) {
        this.owner = owner;
        this.account = account;
        this.number = number;
    }
}
