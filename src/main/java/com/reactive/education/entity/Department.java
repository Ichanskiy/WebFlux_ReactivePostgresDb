package com.reactive.education.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
@NoArgsConstructor
@Table("department")
public class Department {

    @Id
    private Long id;
    private String name;
    private String address;

    private List<Employee> employees = new ArrayList<>();

    public Department addEmployee(Employee employee) {
        employee.setDepartment(this);
        employees.add(employee);
        return this;
    }

    public void removeEmployee(Employee employee) {
        employee.setDepartment(null);
        employees.remove(employee);
    }
}
