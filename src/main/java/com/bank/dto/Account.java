package com.bank.dto;

import com.bank.policy.account.AccountStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Account {

    private Client owner;
    private List<Card> cards;
    private Total total;
    private AccountStatus status;

    public Account(Client owner, List<Card> cards, Total total, AccountStatus status) {
        this.owner = owner;
        this.cards = cards;
        this.total = total;
        this.status = status;
    }
}
