package com.reactive.education.services;

import com.reactive.education.dto.EmployeeDto;
import com.reactive.education.entity.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Mono<EmployeeDto> getEmployeeById(Long id);

    Flux<EmployeeDto> getAllByDepartmentId(Long departmentId);

    Mono<Void> removeEmployeeById(Long id);

    Mono<Employee> saveEmployee(Employee employee);
}
