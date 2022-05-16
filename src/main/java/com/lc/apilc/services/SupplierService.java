package com.lc.apilc.services;

import com.lc.apilc.models.entity.Supplier;
import com.lc.apilc.models.request.SupplierRequest;
import com.lc.apilc.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Transactional
    public Supplier createSupplier(SupplierRequest supplierRequest) {
        Supplier supplier = new Supplier(supplierRequest);
        return this.supplierRepository.save(supplier);
    }

    public List<Supplier> getSuppliers(Example example) {
        return this.supplierRepository.findAll(example);
    }

    public Optional<Supplier> findById(UUID id){
        return this.supplierRepository.findById(id);
    }

    @Transactional
    public Supplier updateSupplier(Supplier supplier){
        return this.supplierRepository.save(supplier);
    }
}
