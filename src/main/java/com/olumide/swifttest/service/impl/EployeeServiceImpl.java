package com.olumide.swifttest.service.impl;

import com.olumide.swifttest.Employee;
import com.olumide.swifttest.repository.EmployeeRepository;
import com.olumide.swifttest.service.EmployeeService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class EployeeServiceImpl  implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Flux<Employee> getAllEmployees() {

        List<Employee> list = employeeRepository.findAll();

        return Flux.fromIterable(list);
    }

    @Override
    public Mono<Employee> getEmployeeById(Long id) {
        Employee employee = employeeRepository.getOne(id);
        return Mono.justOrEmpty(employee);
    }

    @Override
    public Mono<Employee> createOrUpdateEmployee(Employee employee) {
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {

    }
}
