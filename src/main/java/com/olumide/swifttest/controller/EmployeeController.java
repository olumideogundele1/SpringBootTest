package com.olumide.swifttest.controller;

import com.olumide.swifttest.Employee;
import com.olumide.swifttest.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = { "/create", "/" })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Mono<Employee>> create(@RequestBody Employee e) {
      return   new ResponseEntity<>(employeeService.createOrUpdateEmployee(e),HttpStatus.CREATED);
    }

    @GetMapping(value = "/get-employee/{id}")
    public ResponseEntity<Mono<Employee>> getEmployee(@PathVariable Long id){
        Mono<Employee> employeeMono = employeeService.getEmployeeById(id);
        HttpStatus status = (employeeMono != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(employeeMono,status);
    }

    @GetMapping(value ="/get-employees" )
    public ResponseEntity<Flux<Employee>> getAllEmployees(){
        return new ResponseEntity<>(employeeService.getAllEmployees(),HttpStatus.OK);
    }

    @PutMapping("/update-employees")
    public ResponseEntity<Mono<Employee>> updateEmployee(@RequestBody Employee e){
        return new ResponseEntity<>(employeeService.createOrUpdateEmployee(e),HttpStatus.OK);
    }
}
