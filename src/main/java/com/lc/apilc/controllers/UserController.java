package com.lc.apilc.controllers;

import com.lc.apilc.models.request.UserRequest;
import com.lc.apilc.models.entity.Department;
import com.lc.apilc.models.entity.User;
import com.lc.apilc.models.services.DepartmentService;
import com.lc.apilc.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable UUID id) {
        Optional<User> userOp = userService.findById(id);
        if (userOp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
        User user = userOp.get();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserRequest userRequest) {
        if (userService.existsByLogin(userRequest.getLogin())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Login já existe!");
        }

        Optional<Department> optionalDepartment = departmentService.findById(userRequest.getDepartmentId());
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();

            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(
                    new User(userRequest.getName(), userRequest.getLogin(), userRequest.getPassword(), department, userRequest.isAdmin()))
            );
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Departamento inválido!");
    }

}
