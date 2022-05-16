package com.lc.apilc.services;

import com.lc.apilc.enums.StatusWorkOrder;
import com.lc.apilc.exception.WorkOrderNotFoundExcepion;
import com.lc.apilc.models.entity.WorkOrder;
import com.lc.apilc.models.request.WorkOrderRequest;
import com.lc.apilc.repositories.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class WorkOrderService {

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Transactional
    public WorkOrder createWorkOrder(WorkOrderRequest workOrderRequest) {
        WorkOrder workOrder = new WorkOrder(workOrderRequest);
        return this.workOrderRepository.save(workOrder);
    }

    public List<WorkOrder> getWorkOrders() {
        return this.workOrderRepository.findAll();
    }

    @Transactional
    public void updateWorkOrderStatus(UUID id, StatusWorkOrder statusWorkOrder) {
        this.workOrderRepository
                .findById(id)
                .map(
                        workOrder -> {
                            workOrder.setStatus(statusWorkOrder);
                            return workOrderRepository.save(workOrder);
                        }
                ).orElseThrow(WorkOrderNotFoundExcepion::new);
    }
}
