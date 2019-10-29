package com.reactive.education.controllers;

import com.reactive.education.dto.EmployeeDto;
import com.reactive.education.entity.Employee;
import com.reactive.education.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ControllerAPI.EMPLOYEE_CONTROLLER)
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @CrossOrigin
    @GetMapping(value = ControllerAPI.BY_ID)
    public Mono<ResponseEntity<EmployeeDto>> getEmployeeById(@PathVariable(name = "id") Long id) {
        log.info("Get employee by id = " + id);
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .doOnError(Throwable::getMessage);
    }

    @CrossOrigin
    @GetMapping(value = ControllerAPI.BY_DEPARTMENT_ID)
    public Flux<EmployeeDto> getAllEmployeesByDepartmentId(@PathVariable(name = "id") Long id) {
        log.info("Get all employees by department`s id = " + id);
        return employeeService.getAllByDepartmentId(id);
    }

    @CrossOrigin
    @PostMapping(value = ControllerAPI.GENERAL_REQUEST)
    public Mono<Employee> saveEmployee(@RequestBody @Validated Employee employee) {
        log.info("Save employee");
        return employeeService.saveEmployee(employee);
    }

    @CrossOrigin
    @DeleteMapping(value = ControllerAPI.BY_ID)
    public Mono<Void> removeEmployeeById(@PathVariable(name = "id") Long id) {
        log.info("Remove employee by id = " + id);
        return employeeService.removeEmployeeById(id);
    }
}
