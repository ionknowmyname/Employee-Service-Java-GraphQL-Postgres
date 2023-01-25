package com.faithfulolaleru.EmployeeServiceGraphQLPostgres.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryRequest {

    private Integer employeeId;
    private String salary;
}
