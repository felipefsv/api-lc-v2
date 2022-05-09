package com.lc.apilc.controllers;

import com.lc.apilc.models.request.LoginRequest;
import com.lc.apilc.models.entity.User;
import com.lc.apilc.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    @Qualifier("appName")
    private String applicationName;

    @PostMapping
    public Object login(@RequestBody @Valid LoginRequest loginRequest) {
        User user = userService.findByLogin(loginRequest.getLogin());
        if(user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login inválido!" + this.applicationName);
        }
        boolean valid = loginRequest.getPassword().equals(user.getPassword());
        if(valid) {
            return ResponseEntity.status(HttpStatus.OK).body("Login sucesso!" + this.applicationName);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login inválido!");
    }
}
