package com.mangement.dto;

import com.mangement.domain.Division;
import com.mangement.domain.Role;

import javax.validation.constraints.NotBlank;

public record Employee(
        Double salary,
        @NotBlank(message = "employee should have a division")
        Division division,
        @NotBlank(message = "employee should have a role")
        Role role,
        PersonalInformation personalInformation,
        String email) {


}
