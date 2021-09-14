package com.bank.store.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    private LocalDate birthDay;
    private Integer age;
    private Double salary;
    @Enumerated(EnumType.STRING)
    private Role role;

    public EmployeeEntity(String name, String lastName, LocalDate birthDay, Integer age, Double salary, Role role) {
        this.name = name;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.age = age;
        this.salary = salary;
        this.role = role;
    }
}
