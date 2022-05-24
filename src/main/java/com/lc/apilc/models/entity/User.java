package com.lc.apilc.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lc.apilc.models.request.UserRequest;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(UserRequest userRequest) {
        this.name = userRequest.getName();
        this.login = userRequest.getLogin();
        this.password = userRequest.getPassword();
        this.department = userRequest.getDepartment();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isActive = true;
        this.isAdmin = userRequest.isAdmin();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = false, length = 255)
    private String name;
    @Column(nullable = false, unique = true, length = 20)
    private String login;
    @Column(nullable = false, unique = false, length = 255)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(nullable = false, unique = false, length = 1)
    private boolean isAdmin;
    @Column(nullable = false, unique = false, length = 1)
    private boolean isActive;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(targetEntity = Department.class, fetch = FetchType.EAGER)
    private Department department;

}
