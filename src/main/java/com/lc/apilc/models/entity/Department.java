package com.lc.apilc.models.entity;

import com.lc.apilc.models.request.DepartmentRequest;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "departments")
@Data
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    public Department() {

    }

    public Department(DepartmentRequest departmentRequest) {
        this.name = departmentRequest.getName();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = false, length = 255)
    private String name;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
