package com.bank.api.dto.v2;

import com.bank.store.domain.Division;
import com.bank.store.domain.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

public record Employee(
        @NotBlank(message = "name is mandatory")
        String name,
        @JsonProperty(value = "last_name")
        @NotBlank(message = "last name is mandatory")
        String lastName,
        Calendar birthDay,
        @Min(value = 18, message = "minimum employees age is 18")
        @Max(value = 60, message = "maximum employees age is 60")
        Integer age,
        Calendar startDay,
        Double salary,
        @NotBlank(message = "employee should have a division")
        Division division,
        @NotBlank(message = "employee should have a role")
        Role role ) {

        public Employee() {
                this(null, null, null, null, null, null, null, null);
        }

}
