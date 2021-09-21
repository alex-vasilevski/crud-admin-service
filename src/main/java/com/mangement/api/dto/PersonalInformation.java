package com.mangement.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;

public record PersonalInformation(
        @NotBlank(message = "name is mandatory")
        String name,
        @JsonProperty(value = "last_name")
        @NotBlank(message = "last name is mandatory")
        String lastName,
        Calendar birthDay,
        @Min(value = 18, message = "minimum employees age is 18")
        @Max(value = 60, message = "maximum employees age is 60")
        Integer age,
        Calendar startDay
) {
}
