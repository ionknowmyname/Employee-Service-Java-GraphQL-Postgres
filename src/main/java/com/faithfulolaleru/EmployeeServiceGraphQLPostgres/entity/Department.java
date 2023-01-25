package com.faithfulolaleru.EmployeeServiceGraphQLPostgres.entity;

import com.faithfulolaleru.EmployeeServiceGraphQLPostgres.config.ToJsonbConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "departments")
@Data
@AllArgsConstructor
@NoArgsConstructor
// @Builder
public class Department implements Serializable {

    @Id
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_sequence"
    )
    private Integer id;

    @Column(name = "name")
    private String name;

    // @Builder.Default
    // @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    // @Type(type = "jsonb")
    // @Type(value = "List")
     // @Type(JsonBinaryType.class)  // ListArrayType
    // @JdbcTypeCode(SqlTypes.JSON)   // didn't remove the basic attribute error

     // create a class to implement org.hibernate.usertype.UserType, then use as value for @Type

    @Convert(converter = ToJsonbConverter.class)
    @Column(columnDefinition = "jsonb")
    private List<Employee> employees = new ArrayList<>();

    @CreatedDate
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

}
