package com.bank.service.internal;

import com.bank.api.dto.Employee;
import com.bank.api.transformers.spi.EmployeeTransformer;
import com.bank.store.domain.EmployeeEntity;
import com.bank.store.domain.Role;
import com.bank.exception.EmployeeNotFoundException;
import com.bank.store.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeEntityServiceImplTest {

    private static final Long ID = 1L;
    private static final String NAME = "Alex";
    private static final String LAST_NAME = "Vasilevski";
    private static final LocalDate BIRTH_DAY = LocalDate.of(1999, Month.AUGUST, 20);
    private static final Integer AGE = 22;
    private static final Double SALARY = 750.5D;
    private static final Role ROLE = Role.CLERK;

    private static final int PAGE_SIZE = 10;
    private static final String DEFAULT_SORT_PARAMETER = "id";
    private static final String DEFAULT_SORT_DIRECTION = Sort.Direction.ASC.toString();

    @Mock
    private EmployeeRepository repository;

    @Mock
    private EmployeeTransformer transformer;

    @InjectMocks
    private EmployeeServiceImpl service;



    @Test
    public void shouldCreateEmployee(){
        when(transformer.toEntity(any())).thenReturn(defaultEntityEmployee());
        when(repository.save(any())).thenReturn(defaultEntityEmployee());
        assertDoesNotThrow(() -> service.create(defaultDtoEmployee()));
    }

    @Test
    public void shouldFindEmployeeByID() throws EmployeeNotFoundException {
        when(transformer.toDto(any())).thenReturn(defaultDtoEmployee());
        when(repository.findById(ID)).thenReturn(Optional.of(defaultEntityEmployee()));
        Employee actual = service.findById(ID);
        Employee expected = defaultDtoEmployee();

        assertEquals(expected, actual);
    }

    private static Employee defaultDtoEmployee(){
        return new Employee(NAME, LAST_NAME, BIRTH_DAY, AGE, SALARY, ROLE);
    }

    private static EmployeeEntity defaultEntityEmployee(){
        EmployeeEntity employeeEntity = new EmployeeEntity(NAME, LAST_NAME, BIRTH_DAY, AGE, SALARY, ROLE);
        employeeEntity.setId(ID);
        return employeeEntity;
    }

}
