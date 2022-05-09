package com.lc.apilc.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "suppliers")
@Data
public class Supplier implements Serializable  {
    private static final long serialVersionUID = 1L;

    public Supplier() {

    }

    public Supplier(String name, String document, String phone, String email, String address) {
        this.name = name;
        this.document = document;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = false, length = 255)
    private String name;

    @Column(nullable = false, unique = true, length = 255)
    private String document;

    @Column(nullable = false, unique = false, length = 255)
    private String phone;

    @Column(nullable = false, unique = false, length = 255)
    private String email;

    @Column(nullable = false, unique = false, length = 255)
    private String address;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
