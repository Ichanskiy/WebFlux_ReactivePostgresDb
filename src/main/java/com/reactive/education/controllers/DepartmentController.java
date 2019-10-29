package com.reactive.education.controllers;

import com.reactive.education.dto.DepartmentDto;
import com.reactive.education.entity.Department;
import com.reactive.education.services.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ControllerAPI.DEPARTMENT_CONTROLLER)
@Slf4j
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @CrossOrigin
    @GetMapping(value = ControllerAPI.ALL)
    public Flux<DepartmentDto> getAllDepartments() {
        log.info("Get all departments");
        return departmentService.getAll();
    }

    @CrossOrigin
    @GetMapping(value = ControllerAPI.BY_ID)
    public Mono<ResponseEntity<DepartmentDto>> getDepartmentById(@PathVariable(name = "id") Long id) {
        log.info("Get department by id = " + id);
        return departmentService.getDepartmentById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin
    @PostMapping(value = ControllerAPI.GENERAL_REQUEST)
    public Mono<Department> saveDepartment(@RequestBody @Validated Department department) {
        return departmentService.saveDepartment(department);
    }

    @CrossOrigin
    @DeleteMapping(value = ControllerAPI.BY_ID)
    public Mono<ResponseEntity<Void>> removeDepartmentById(@PathVariable(name = "id") Long id) {
        log.info("Remove department by id = " + id);
        return departmentService.removeDepartmentById(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .onErrorReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
