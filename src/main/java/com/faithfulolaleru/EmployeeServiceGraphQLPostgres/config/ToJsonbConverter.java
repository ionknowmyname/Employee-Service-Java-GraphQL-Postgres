package com.faithfulolaleru.EmployeeServiceGraphQLPostgres.config;

import com.faithfulolaleru.EmployeeServiceGraphQLPostgres.entity.Employee;
import com.google.gson.Gson;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter(autoApply = true)
public class ToJsonbConverter implements AttributeConverter<List<Employee>, String> {

    private final static Gson GSON = new Gson();

    @Override
    public String convertToDatabaseColumn(List<Employee> employee) {
        return GSON.toJson(employee);
    }

    @Override
    public List<Employee> convertToEntityAttribute(String dbData) {
        return (List<Employee>) GSON.fromJson(dbData, Employee.class);
    }
}
