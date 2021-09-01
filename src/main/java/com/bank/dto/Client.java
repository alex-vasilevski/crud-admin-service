package com.bank.dto;

import com.bank.policy.rights.Right;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;


@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Client implements Operator {

    private String name;
    private String lastName;
    private Set<Right> rights;
    private Set<Account> accountEntities;

    public Client(String name, String lastName, Set<Right> rights, Set<Account> accountEntities) {
        this.name = name;
        this.lastName = lastName;
        this.rights = rights;
        this.accountEntities = accountEntities;
    }

}
