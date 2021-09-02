package com.bank.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Administrator {

    @Id
    private Long id;
    private String name;
    private String lastName;

    public Administrator(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
}
