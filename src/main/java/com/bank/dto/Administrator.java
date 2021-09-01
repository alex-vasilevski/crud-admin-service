package com.bank.dto;


import com.bank.policy.rights.Right;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Administrator implements Operator {

    private String name;
    private String lastName;
    private Set<Right> rights;

    public Administrator(String name, String lastName, Set<Right> rights) {
        this.name = name;
        this.lastName = lastName;
        this.rights = rights;
    }
}
