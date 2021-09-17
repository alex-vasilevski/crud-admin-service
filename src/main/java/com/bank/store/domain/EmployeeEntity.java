package com.bank.store.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

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
    private Calendar birthDay;
    private Integer age;
    private Calendar startDay;
    private Double salary;
    @Enumerated(EnumType.STRING)
    private Division division;
    @Enumerated(EnumType.STRING)
    private Role role;

    public EmployeeEntity(String name, String lastName, Calendar birthDay,
                          Integer age, Calendar startDay, Double salary,
                          Division division, Role role) {
        this.name = name;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.age = age;
        this.startDay = startDay;
        this.salary = salary;
        this.division = division;
        this.role = role;
    }
}
