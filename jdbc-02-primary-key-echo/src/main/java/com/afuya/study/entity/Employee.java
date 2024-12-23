package com.afuya.study.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author: afuya
 * @program: study-java-jdbc
 * @date: 2024/12/23 22:26
 */

@Data
@Builder
public class Employee {
    private Integer empId;
    private String empName;
    private Double empSalary;
    private Integer empAge;
}
