package com.faithfulolaleru.EmployeeServiceGraphQLPostgres.controller;

import com.faithfulolaleru.EmployeeServiceGraphQLPostgres.entity.Department;
import com.faithfulolaleru.EmployeeServiceGraphQLPostgres.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;


    // @SchemaMapping(typeName = "Mutation", value = "createNewDepartment")
    @MutationMapping
    public Department createNewDepartment(@Argument String name) {
        Optional<Department> foundDept = departmentRepository.findDepartmentByName(name);

        if(foundDept.isPresent()) { throw new RuntimeException("Department with name already exists"); }

        Department newDept = new Department();
        newDept.setName(name);
        newDept.setCreatedAt(OffsetDateTime.now());
        newDept.setUpdatedAt(OffsetDateTime.now());

        return departmentRepository.save(newDept);
    }

    @QueryMapping
    public List<Department> getAllDepartments() {

        return departmentRepository.findAll();
    }

    @QueryMapping
    public Department getDepartmentById(Integer id) {

        return departmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Department with id not found"));
    }
}
