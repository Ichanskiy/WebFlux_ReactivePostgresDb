package com.reactive.education.entity;

import com.reactive.education.entity.enums.Position;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table("employee")
public class Employee {

    @Id
    private Long id;
    private String email;
    private String name;
    private boolean married;
    private Date birthday;
    private Position position;
    private Department department;
}
