package com.bank.domain.operators;

import com.bank.domain.AccountEntity;
import com.bank.policy.rights.Right;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;


@Entity
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class ClientEntity implements OperatorEntity {
    @Id
    private Long id;
    private String name;
    private String lastName;
    private Set<Right> rights;
    private Set<AccountEntity> accountEntities;

    public ClientEntity(String name, String lastName, Set<Right> rights, Set<AccountEntity> accountEntities) {
        this.name = name;
        this.lastName = lastName;
        this.rights = rights;
        this.accountEntities = accountEntities;
    }

}
