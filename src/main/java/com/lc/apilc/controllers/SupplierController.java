package com.lc.apilc.controllers;

import com.lc.apilc.models.entity.Supplier;
import com.lc.apilc.models.request.SupplierRequest;
import com.lc.apilc.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping()
    public List<Supplier> findSuppliers(Supplier supplier) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Supplier> example = Example.of(supplier, matcher);
        return supplierService.getSuppliers(example);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object createSupplier(@RequestBody @Valid SupplierRequest supplierRequest) {
        return supplierService.createSupplier(supplierRequest);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> udpateSupplier(@PathVariable UUID id, @RequestBody Supplier supplier) {
        return supplierService
                .findById(id)
                .map(s -> {
                    supplier.setId(s.getId());
                    supplierService.updateSupplier(supplier);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
