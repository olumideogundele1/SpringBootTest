package com.olumide.swifttest;

import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@Entity
public class Employee {

    private Long id;

    private String name;

    private BigDecimal salary;
}
