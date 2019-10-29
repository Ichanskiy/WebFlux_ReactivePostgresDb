package com.reactive.education.repository;

import com.reactive.education.entity.Department;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DepartmentRepository extends ReactiveCrudRepository<Department, Long> {
    Flux<Department> findAllByOrderByNameAsc();
}
