package com.lc.apilc.models.services;

import com.lc.apilc.models.entity.Supplier;
import com.lc.apilc.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier createSupplier(Supplier supplier) {
        return this.supplierRepository.save(supplier);
    }

    public List<Supplier> getSuppliers() {
        return this.supplierRepository.findAll();
    }
}
