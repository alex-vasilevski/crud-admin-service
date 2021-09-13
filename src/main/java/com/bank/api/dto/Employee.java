package com.bank.api.dto;

import com.bank.store.domain.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Employee {

    @NotBlank(message = "name is mandatory")
    private String name;
    @JsonProperty(value = "last_name")
    @NotBlank(message = "last name is mandatory")
    private String lastName;
    @JsonProperty(value = "birth_day")
    private LocalDate birthDay;
    @Min(value = 18, message = "minimum employees age is 18")
    @Max(value = 60, message = "maximum employees age is 60")
    private Integer age;
    private Double salary;
    @NotBlank(message = "employee should have a role")
    private Role role;
}
