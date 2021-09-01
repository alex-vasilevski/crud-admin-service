package com.bank.domain.operators;

import com.bank.policy.rights.Right;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class AdministratorEntity implements OperatorEntity {

    @Id
    private Long id;
    private String name;
    private String lastName;
    private Set<Right> rights;

    public AdministratorEntity(String name, String lastName, Set<Right> rights) {
        this.name = name;
        this.lastName = lastName;
        this.rights = rights;
    }
}
