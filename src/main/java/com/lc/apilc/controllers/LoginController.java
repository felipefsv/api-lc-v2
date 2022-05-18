package com.lc.apilc.controllers;

import com.lc.apilc.configuration.InternationalizationConfig;
import com.lc.apilc.enums.ErrorCodes;
import com.lc.apilc.exception.LcException;
import com.lc.apilc.models.request.LoginRequest;
import com.lc.apilc.models.entity.User;
import com.lc.apilc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Object login(@RequestBody @Valid LoginRequest loginRequest) {
        User user = userService.findByLogin(loginRequest.getLogin());
        if (user == null) {
            throw new LcException("Login inválido!", ErrorCodes.USUARIO_INVALIDO);
        }
        boolean valid = loginRequest.getPassword().equals(user.getPassword());
        if (valid) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        throw new LcException("Login inválido!", ErrorCodes.USUARIO_INVALIDO);
    }
}
