package com.reactive.education.services;

import com.reactive.education.dto.DepartmentDto;
import com.reactive.education.entity.Department;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepartmentService {
    Flux<DepartmentDto> getAll();

    Mono<DepartmentDto> getDepartmentById(Long id);

    Mono<Department> saveDepartment(Department department);

    Mono<Void> removeDepartmentById(Long id);
}
