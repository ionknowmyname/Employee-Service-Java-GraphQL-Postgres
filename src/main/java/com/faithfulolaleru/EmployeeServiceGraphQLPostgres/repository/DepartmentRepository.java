package com.faithfulolaleru.EmployeeServiceGraphQLPostgres.repository;

import com.faithfulolaleru.EmployeeServiceGraphQLPostgres.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Optional<Department> findDepartmentByName(String name);
}
