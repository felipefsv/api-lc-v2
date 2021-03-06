package com.lc.apilc.controllers;

import com.lc.apilc.enums.StatusWorkOrder;
import com.lc.apilc.models.entity.WorkOrder;
import com.lc.apilc.models.request.WorkOrderRequest;
import com.lc.apilc.models.request.WorkOrderStatusRequest;
import com.lc.apilc.services.PostMarkService;
import com.lc.apilc.services.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("workorder")
public class WorkOrderController {
    @Autowired
    private WorkOrderService workOrderService;

    @Autowired
    private PostMarkService postMarkService;

    @GetMapping()
    public List<WorkOrder> getWorkOrders() {
        return workOrderService.getWorkOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object createWorkOrder(@RequestBody @Valid WorkOrderRequest workOrderRequest) {
        WorkOrder workOrder = workOrderService.createWorkOrder(workOrderRequest);
//        postMarkService.sendNewWorkOrderEmail(workOrder);
        return workOrder;
    }

    @PatchMapping("{id}")
    public void updateSatus(@PathVariable UUID id, @RequestBody @Valid WorkOrderStatusRequest workOrderStatusRequest) {
        this.workOrderService.updateWorkOrderStatus(id, StatusWorkOrder.valueOf(workOrderStatusRequest.getStatus()));
    }

}
