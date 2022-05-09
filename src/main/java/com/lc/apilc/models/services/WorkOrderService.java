package com.lc.apilc.models.services;

import com.lc.apilc.models.entity.WorkOrder;
import com.lc.apilc.models.request.WorkOrderRequest;
import com.lc.apilc.repositories.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkOrderService {

    @Autowired
    private WorkOrderRepository workOrderRepository;

    public WorkOrder createWorkOrder(WorkOrderRequest workOrderRequest) {
        WorkOrder workOrder = new WorkOrder(workOrderRequest);
        return this.workOrderRepository.save(workOrder);
    }

    public List<WorkOrder> getWorkOrders() {
        return this.workOrderRepository.findAll();
    }
}
