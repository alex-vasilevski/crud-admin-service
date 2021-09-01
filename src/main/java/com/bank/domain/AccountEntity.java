package com.bank.domain;

import com.bank.domain.operators.ClientEntity;
import com.bank.policy.account.AccountStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class AccountEntity {
    @Id
    private Long id;
    private ClientEntity owner;
    private List<CardEntity> cards;
    private TotalEntity total;
    private AccountStatus status;

    public AccountEntity(ClientEntity owner, List<CardEntity> cards, TotalEntity total, AccountStatus status) {
        this.owner = owner;
        this.cards = cards;
        this.total = total;
        this.status = status;
    }
}
