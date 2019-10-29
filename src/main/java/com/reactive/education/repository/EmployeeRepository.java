package com.reactive.education.repository;

import com.reactive.education.entity.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Long> {
    Flux<Employee> getAllByDepartmentIdOrderByEmail(Long departmentId);

    Mono<Employee> getEmployeeByEmail(String email);

    Mono<Boolean> existsByEmail(String email);
}
