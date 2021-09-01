package com.bank.dto;

import ch.qos.logback.core.net.server.Client;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Card {

    private Client owner;
    private Account account;
    private Long number;

    public Card(Client owner, Account account, Long number) {
        this.owner = owner;
        this.account = account;
        this.number = number;
    }
}
