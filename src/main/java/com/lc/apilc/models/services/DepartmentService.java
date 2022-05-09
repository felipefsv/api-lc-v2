package com.lc.apilc.models.services;

import com.lc.apilc.models.entity.Department;
import com.lc.apilc.models.entity.User;
import com.lc.apilc.repositories.DepartmentRepository;
import com.lc.apilc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Department createDepartment(Department department) {
        return this.departmentRepository.save(department);
    }

    public Optional<Department> findById(UUID id) {
        return this.departmentRepository.findById(id);
    }

    public List<Department> getDepartments() {
        return this.departmentRepository.findAll();
    }

    public Set<User> getUsersFromDepartment(UUID id) {
        return this.userRepository.findByDepartmentId(id);
    }

}
