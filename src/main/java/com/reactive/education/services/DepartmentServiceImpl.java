package com.reactive.education.services;

import com.reactive.education.dto.DepartmentDto;
import com.reactive.education.entity.Department;
import com.reactive.education.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Predicate;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Flux<DepartmentDto> getAll() {
        return departmentRepository.findAllByOrderByNameAsc()
                .flatMap(department -> Flux.just(new DepartmentDto(department.getId(),
                        department.getName(),
                        department.getAddress())));
    }

    @Transactional
    public Mono<DepartmentDto> getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .flatMap(department -> Mono.just(new DepartmentDto(department.getId(),
                        department.getName(),
                        department.getAddress())));
    }

    @Transactional
    public Mono<Department> saveDepartment(Department department) {
        Predicate<Long> idIsNul = Objects::isNull;
        return idIsNul.test(department.getId()) ?
                departmentRepository.save(department)
                :
                departmentRepository.findById(department.getId())
                        .flatMap(departmentDb -> {
                            departmentDb.setAddress(department.getAddress());
                            departmentDb.setName(department.getName());
                            return departmentRepository.save(departmentDb);
                        });
    }

    @Transactional
    public Mono<Void> removeDepartmentById(Long id) {
        return departmentRepository.deleteById(id);
    }
}
