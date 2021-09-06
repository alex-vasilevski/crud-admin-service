package com.bank.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Employee {

    @Id
    private Long id;
    private String name;
    private String lastName;
    private LocalDate birthDay;
    private Integer age;
    private Double salary;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Employee(String name, String lastName, Role role) {
        this.name = name;
        this.lastName = lastName;
        this.role = role;
    }
}
