package com.lc.apilc.models.entity;

import com.lc.apilc.models.request.SupplierRequest;
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

    public Supplier(SupplierRequest supplierRequest) {
        this.name = supplierRequest.getName();
        this.document = supplierRequest.getDocument();
        this.phone = supplierRequest.getPhone();
        this.email = supplierRequest.getEmail();
        this.address = supplierRequest.getAddress();
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
