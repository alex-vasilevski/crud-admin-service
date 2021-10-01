package com.mangement.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Calendar;

@Entity
@Table(name = "personal_info")
@NoArgsConstructor
@Getter
@Setter
public class PersonalInformationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Calendar birthDay;
    private Integer age;
    @Temporal(TemporalType.DATE)
    private Calendar startDay;
    private String emailAddress;

    public PersonalInformationEntity(String name, String lastName,
                                     Calendar birthDay, Integer age,
                                     Calendar startDay, String emailAddress) {
        this.name = name;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.age = age;
        this.startDay = startDay;
        this.emailAddress = emailAddress;
    }
}
