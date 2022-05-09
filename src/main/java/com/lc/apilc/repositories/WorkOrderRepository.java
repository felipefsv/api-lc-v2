package com.lc.apilc.repositories;

import com.lc.apilc.models.entity.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, UUID> {

}
