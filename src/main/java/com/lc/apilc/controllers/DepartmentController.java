package com.lc.apilc.controllers;

import com.lc.apilc.models.entity.Department;
import com.lc.apilc.models.request.DepartmentRequest;
import com.lc.apilc.models.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping()
    public List<Department> getDepartments() {

//        List<Department> departments = departmentService.getDepartments();
//        List<DepartmentResponse> departmentResponse = new ArrayList<DepartmentResponse>();
//        departments.forEach(department -> {
//            DepartmentResponse departmentResponse1 = new DepartmentResponse();
//            Set<User> usersFromDepartment = this.departmentService.getUsersFromDepartment(department.getId());
//
//            departmentResponse1.setId(department.getId());
//            departmentResponse1.setName(department.getName());
//            departmentResponse1.setCreatedAt(department.getCreatedAt());
//            departmentResponse1.setUpdatedAt(department.getUpdatedAt());
//            departmentResponse1.setUsers(usersFromDepartment);
//
//            departmentResponse.add(departmentResponse1);
//        });
//
//        return ResponseEntity.status(HttpStatus.OK).body(departmentResponse);

        return departmentService.getDepartments();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object createDepartment(@RequestBody @Valid DepartmentRequest departmentRequest) {
        return departmentService.createDepartment(departmentRequest);
    }

    @DeleteMapping("/{id}")
    public Object deleteDepartment(@PathVariable UUID id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}