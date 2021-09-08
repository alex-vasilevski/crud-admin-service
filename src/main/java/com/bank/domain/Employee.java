package com.bank.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "last name is mandatory")
    private String lastName;
    private LocalDate birthDay;
    @Min(value = 18, message = "minimum employees age is 18")
    @Max(value = 60, message = "maximum employees age is 60")
    private Integer age;
    private Double salary;
    @NotBlank(message = "employee should have a role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public Employee(String name, String lastName, LocalDate birthDay, Integer age, Double salary, Role role) {
        this.name = name;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.age = age;
        this.salary = salary;
        this.role = role;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name)
                && Objects.equals(lastName, employee.lastName)
                && Objects.equals(birthDay, employee.birthDay)
                && Objects.equals(age, employee.age)
                && Objects.equals(salary, employee.salary)
                && role == employee.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, birthDay, age, salary, role);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", age=" + age +
                ", salary=" + salary +
                ", role=" + role +
                '}';
    }
}
