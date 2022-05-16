package com.lc.apilc.controllers;

import com.lc.apilc.models.request.UserRequest;
import com.lc.apilc.models.entity.User;
import com.lc.apilc.models.services.DepartmentService;
import com.lc.apilc.models.services.UserService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public User getUser(@PathVariable UUID id) {
        return userService
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!")
                );
    }

    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(consumes = {"application/json"}, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createUser(@RequestBody @Valid UserRequest userRequest) {
        if (userService.existsByLogin(userRequest.getLogin())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Login já existe!");
        }
        return userService.createUser(userRequest);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> udpateUser(@PathVariable UUID id, @RequestBody User user) {
        return userService
                .findById(id)
                .map(u -> {
                    user.setId(u.getId());
                    user.setPassword(u.getPassword());
                    userService.updateUser(user);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deleteUser(@PathVariable UUID id) {
        return userService
                .findById(id)
                .map(u -> {
                    this.userService.deleteUser(u.getId());
                    return u;
                })
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!")
                );
    }

}
