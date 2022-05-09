package com.lc.apilc.controllers;

import com.lc.apilc.models.entity.WorkOrder;
import com.lc.apilc.models.request.WorkOrderRequest;
import com.lc.apilc.models.services.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("workorder")
public class WorkOrderController {

    @Autowired
    WorkOrderService workOrderService;

    @GetMapping()
    public ResponseEntity<List<WorkOrder>> getWorkOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(workOrderService.getWorkOrders());
    }

    @PostMapping
    public ResponseEntity<Object> createWorkOrder(@RequestBody @Valid WorkOrderRequest workOrderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(workOrderService.createWorkOrder(
                new WorkOrder(
                        workOrderRequest.getStatus(),
                        workOrderRequest.getClient(),
                        workOrderRequest.getUser()
                )));
    }

}
