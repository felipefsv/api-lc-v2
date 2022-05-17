package com.lc.apilc.models.entity;

import com.lc.apilc.models.request.ClientRequest;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Data
public class Client implements Serializable  {
    private static final long serialVersionUID = 1L;

    public Client() {

    }

    public Client(ClientRequest clientRequest) {
        this.name = clientRequest.getName();
        this.document = clientRequest.getDocument();
        this.phone = clientRequest.getPhone();
        this.email = clientRequest.getEmail();
        this.address = clientRequest.getAddress();
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

