package com.lc.apilc.models.entity;

import com.lc.apilc.models.request.WorkOrderRequest;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "workOrders")
@Data
public class WorkOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    public WorkOrder() {

    }

    public WorkOrder(WorkOrderRequest workOrderRequest) {
        this.status = workOrderRequest.getStatus();
        this.client = workOrderRequest.getClient();
        this.user = workOrderRequest.getUser();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = false, length = 255)
    private String status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(targetEntity = Client.class, fetch = FetchType.EAGER)
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}

