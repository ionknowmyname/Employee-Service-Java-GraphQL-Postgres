package com.faithfulolaleru.EmployeeServiceGraphQLPostgres.controller;

import com.faithfulolaleru.EmployeeServiceGraphQLPostgres.dto.EmployeeRequest;
import com.faithfulolaleru.EmployeeServiceGraphQLPostgres.entity.Department;
import com.faithfulolaleru.EmployeeServiceGraphQLPostgres.entity.Employee;
import com.faithfulolaleru.EmployeeServiceGraphQLPostgres.repository.DepartmentRepository;
import com.faithfulolaleru.EmployeeServiceGraphQLPostgres.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.function.Function;

@Controller
// @AllArgsConstructor
@RequiredArgsConstructor
public class EmployeeController {

     private final EmployeeRepository employeeRepository;

     private final DepartmentRepository departmentRepository;


    /*

        Function<EmployeeRequest, Employee> mapping = m -> {
            Department foundDept = departmentRepository.findById(m.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department with id not found"));

            return Employee.builder()
                    .name(m.getName())
                    .salary(m.getSalary())
                    .age(m.getAge())
                    .department(foundDept)
                    .createdAt(Instant.now())
                    .updatedAt(Instant.now())
                    .build();
        };

    */

    // @SchemaMapping(typeName = "Mutation", value = "createNewEmployee")
    @MutationMapping  // if method name is same as graph query method name, no need to include it as value
    @Transactional
    public Employee createNewEmployee(@Argument EmployeeRequest request) {

        // make sure department exist for the dept id from request
        Department foundDept = departmentRepository.findById(request.getDepartmentId())
            .orElseThrow(() -> new RuntimeException("Department with id not found"));


        Employee buildedEmployee = Employee.builder()
            .name(request.getName())
            .salary(request.getSalary())
            .age(request.getAge())
            .departmentId(request.getDepartmentId())
            .createdAt(OffsetDateTime.now())
            .updatedAt(OffsetDateTime.now())
            .build();

        Employee savedEmployee = employeeRepository.save(buildedEmployee);

        // add employee to that department employees list
        List<Employee> employeeList = foundDept.getEmployees();
        employeeList.add(savedEmployee);
        foundDept.setEmployees(employeeList);

        departmentRepository.save(foundDept);


        // return employeeRepository.save(mapping.apply(request));

         return savedEmployee;
    }

    @QueryMapping
    public List<Employee> getAllEmployeesByName(@Argument String name) {

        return employeeRepository.findAllByName(name);
    }
}
