package com.olumide.swifttest.service.impl;

import com.olumide.swifttest.Employee;
import com.olumide.swifttest.repository.EmployeeRepository;
import com.olumide.swifttest.service.EmployeeService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

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

        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
        if(optionalEmployee.isPresent()){
            Employee updateEmployee = optionalEmployee.get();
            updateEmployee.setSalary(employee.getSalary());
            updateEmployee.setName(employee.getName());

            updateEmployee = employeeRepository.save(updateEmployee);
            return Mono.just(updateEmployee);
        }else{
            employee = employeeRepository.save(employee);
            return Mono.just(employee);
        }

    }

    @Override
    public void deleteEmployee(Long id) throws NotFoundException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            employeeRepository.deleteById(id);
        }else {
            throw new NotFoundException("No employee record exist for given id");
        }
    }
}
