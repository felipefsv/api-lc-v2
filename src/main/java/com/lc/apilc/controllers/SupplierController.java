package com.lc.apilc.controllers;

import com.lc.apilc.models.entity.Supplier;
import com.lc.apilc.models.request.SupplierRequest;
import com.lc.apilc.models.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping()
    public List<Supplier> getSuppliers() {
        return supplierService.getSuppliers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object createSupplier(@RequestBody @Valid SupplierRequest supplierRequest) {
        return supplierService.createSupplier(supplierRequest);
    }
}
