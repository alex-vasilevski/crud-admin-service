package com.bank.service.internal;

import com.bank.domain.Employee;
import com.bank.domain.Role;
import com.bank.exception.CreateEmployeeException;
import com.bank.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.validation.Validator;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.print.attribute.standard.MediaSize;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

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
    private Set<Validator> validators;

    @InjectMocks
    EmployeeServiceImpl service;

    @Test
    public void shouldCreateEmployee(){
        when(repository.save(defaultDtoEmployee())).thenReturn(defaultEntityEmployee());
        assertDoesNotThrow(() -> service.create(defaultDtoEmployee()));
    }

    @Test
    public void shouldThrowExceptionWhenTryingToCreateEmployeeAndAgeValidationWasNotPassed(){
        Employee employee = defaultDtoEmployee();
        employee.setAge(15);
        when(validators.stream()).thenReturn(Stream.of(new EmployeeAgeValidator()));
        assertThrows(CreateEmployeeException.class, () -> service.create(employee));
    }

    @Test
    public void shouldFindEmployeeByID(){
        when(repository.findById(ID)).thenReturn(Optional.of(defaultEntityEmployee()));
        Optional<Employee> actual = service.findById(ID);
        Optional<Employee> expected = Optional.of(defaultEntityEmployee());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindAllMatchingEmployeesAndSortByDefaultWithDefaultDirection(){
        Sort sort = Sort.by(Sort.Direction.valueOf(DEFAULT_SORT_DIRECTION), DEFAULT_SORT_PARAMETER);
        Pageable pageable = PageRequest.ofSize(PAGE_SIZE).withSort(sort);
        Page<Employee> page = new PageImpl<Employee>((List.of(defaultEntityEmployee())));
        when(repository.findAll(Example.of(defaultDtoEmployee()), pageable)).thenReturn(page);
    }

    private static Employee defaultDtoEmployee(){
        return new Employee(NAME, LAST_NAME, BIRTH_DAY, AGE, SALARY, ROLE);
    }

    private static Employee defaultEntityEmployee(){
        Employee employee = new Employee(NAME, LAST_NAME, BIRTH_DAY, AGE, SALARY, ROLE);
        employee.setId(ID);
        return employee;
    }

}
