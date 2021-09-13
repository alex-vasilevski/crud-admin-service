package com.bank.service.internal;

import com.bank.store.domain.EmployeeEntity;
import com.bank.store.domain.Role;
import com.bank.exception.EmployeeNotFoundException;
import com.bank.store.repository.EmployeeRepository;
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

    @InjectMocks
    private EmployeeServiceImpl service;

    @Test
    public void shouldCreateEmployee(){
        when(repository.save(defaultDtoEmployee())).thenReturn(defaultEntityEmployee());
        assertDoesNotThrow(() -> service.create(defaultDtoEmployee()));
    }

    @Test
    public void shouldFindEmployeeByID() throws EmployeeNotFoundException {
        when(repository.findById(ID)).thenReturn(Optional.of(defaultEntityEmployee()));
        EmployeeEntity actual = service.findById(ID);
        EmployeeEntity expected = defaultEntityEmployee();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("searchCriteriaCases")
    public void shouldFindAllMatchingEmployeesAndSortByDefaultWithDefaultDirection(EmployeeEntity searchCriteria) throws EmployeeNotFoundException {
        Sort sort = Sort.by(Sort.Direction.valueOf(DEFAULT_SORT_DIRECTION), DEFAULT_SORT_PARAMETER);
        Pageable pageable = PageRequest.ofSize(PAGE_SIZE).withSort(sort);
        Page<EmployeeEntity> expected = new PageImpl<EmployeeEntity>((List.of(defaultEntityEmployee())));
        when(repository.findAll(Example.of(searchCriteria), pageable)).thenReturn(expected);

        Page<EmployeeEntity> actual = service.findAllMatchingAndSort(searchCriteria, DEFAULT_SORT_DIRECTION, Collections.singleton(DEFAULT_SORT_PARAMETER));
        assertEquals(expected, actual);
    }

    private static Stream<EmployeeEntity> searchCriteriaCases(){
        return Stream.of(
                new EmployeeEntity(NAME, null, null, null, null, null),
                new EmployeeEntity(null, LAST_NAME, null, null, null, null),
                new EmployeeEntity(null, null, BIRTH_DAY, null, null, null),
                new EmployeeEntity(null, null, null, AGE, null, null),
                new EmployeeEntity(null, null, null, null, SALARY, null),
                new EmployeeEntity(null, null, null, null, null, ROLE),
                new EmployeeEntity(null, null, null, null, null, null)
        );
    }

    private static EmployeeEntity defaultDtoEmployee(){
        return new EmployeeEntity(NAME, LAST_NAME, BIRTH_DAY, AGE, SALARY, ROLE);
    }

    private static EmployeeEntity defaultEntityEmployee(){
        EmployeeEntity employeeEntity = new EmployeeEntity(NAME, LAST_NAME, BIRTH_DAY, AGE, SALARY, ROLE);
        employeeEntity.setId(ID);
        return employeeEntity;
    }

}
