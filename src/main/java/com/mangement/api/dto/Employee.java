package com.mangement.api.dto;

import com.mangement.store.domain.employee.Division;
import com.mangement.store.domain.employee.Role;

import javax.validation.constraints.NotBlank;

public record Employee(

        Double salary,
        @NotBlank(message = "employee should have a division")
        Division division,
        @NotBlank(message = "employee should have a role")
        Role role,
        PersonalInformation personalInformation,
        String email) {

        public Employee() {
                this(null, null, null, null, null);
        }

}
