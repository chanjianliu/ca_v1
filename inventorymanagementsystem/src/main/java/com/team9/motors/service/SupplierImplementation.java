package com.team9.motors.service;

import com.team9.motors.interfacemethods.SupplierInterface;
import com.team9.motors.model.Supplier;
import com.team9.motors.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class SupplierImplementation implements SupplierInterface {
    @Autowired
    SupplierRepository srepo;

    public Page<Supplier> listAllSuppliers(int pageNumber) {
        Pageable pageable= PageRequest.of(pageNumber - 1, 5);
        return srepo.findAll(pageable);
    }

    @Override
    public void createSupplier(Supplier supplier) {
        srepo.save(supplier);
    }

    @Override
    @Transactional
    public Supplier findSupplierById(Integer id) {
        return srepo.findById(id).get();
    }

    @Override
    @Transactional
    public void deleteSupplier(Supplier member) {
        srepo.delete(member);
    }

    @Override
    public List<Supplier> listAllSuppliers() {
        return srepo.findAll();
    }
}