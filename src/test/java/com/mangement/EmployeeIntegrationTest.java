package com.mangement;

import com.mangement.dto.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeIntegrationTest {

    private static final String GET_EMPLOYEE_BY_ID = "/employees/1";
    private static final String GET_ALL_MATCHING_EMPLOYEES = "/employees";
    private static final String GET_ALL_EMPLOYEES_ASSIGNED_ON_PROJECT = "/employees/1";
    private static final String PUT_EMPLOYEE = "/employees/1";
    private static final String POST_NEW_EMPLOYEE = "/employees";
    private static final String DELETE_EMPLOYEE = "/employees/1";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void shouldPerformGetMethodOnEndPoint_GET_EMPLOYEE_BY_ID_andGet2xxStatus() throws Exception {


    }

}
