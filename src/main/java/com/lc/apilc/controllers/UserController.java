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
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{id}")
    public Object getUser(@PathVariable UUID id) {
        Optional<User> userOp = userService.findById(id);
        if (userOp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
        User user = userOp.get();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object createUser(@RequestBody @Valid UserRequest userRequest) {
        if (userService.existsByLogin(userRequest.getLogin())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Login já existe!");
        }
        return userService.createUser(userRequest);
    }

}
