package com.olumide.swifttest;

import com.olumide.swifttest.controller.EmployeeController;
import com.olumide.swifttest.repository.EmployeeRepository;
import com.olumide.swifttest.service.impl.EployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;


@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = EmployeeController.class)
@Import(EployeeServiceImpl.class)
public class EmployerTest {

    @MockBean
    EmployeeRepository employeeRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void createTestEmployee(){
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Test");
        employee.setSalary(BigDecimal.valueOf(1000));


         Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
       // Mockito.doReturn(Mono.just(employee)).when(employeeRepository.save())


        webTestClient.post().uri("/employee/create").contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(employee)).exchange().expectStatus().isCreated();
        Mockito.verify(employeeRepository,Mockito.times(1)).save(employee);
    }

    @Test
    void testGetEmployeeById(){
        Employee e = new Employee();
        e.setId(1L);
        e.setName("Test");
        e.setSalary(BigDecimal.valueOf(1000));

        Mockito.when(employeeRepository.getOne(1L)).thenReturn(e);

        webTestClient.get().uri("/employee/get-employee/{id}",1L)
                .exchange().expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isNotEmpty()
                .jsonPath("$.id").isEqualTo(1L)
                .jsonPath("$.name").isEqualTo("Test")
                .jsonPath("$.salary").isEqualTo(1000);

        Mockito.verify(employeeRepository,Mockito.times(1)).getOne(1L);
    }

    @Test
    void testDeleteEmployee(){
        Employee e = new Employee();
        e.setId(1L);
    }
}
