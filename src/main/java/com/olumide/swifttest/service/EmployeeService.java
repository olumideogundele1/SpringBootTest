package com.olumide.swifttest.service;

import com.olumide.swifttest.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

    Flux<Employee> getAllEmployees();

    Mono<Employee> getEmployeeById(Long id);

    Mono<Employee> createOrUpdateEmployee(Employee employee);

    void deleteEmployee(Long id);
}
