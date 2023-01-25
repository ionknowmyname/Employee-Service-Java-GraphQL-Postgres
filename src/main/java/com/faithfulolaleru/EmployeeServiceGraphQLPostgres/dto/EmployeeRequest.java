package com.faithfulolaleru.EmployeeServiceGraphQLPostgres.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    private String name;
    private String salary;
    private int age;
    private Integer departmentId;
}
