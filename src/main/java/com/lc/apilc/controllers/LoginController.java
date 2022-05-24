package com.lc.apilc.controllers;

import com.lc.apilc.enums.ErrorCodes;
import com.lc.apilc.exception.LcException;
import com.lc.apilc.models.request.LoginRequest;
import com.lc.apilc.models.entity.User;
import com.lc.apilc.models.response.TokenResponse;
import com.lc.apilc.services.JwtService;
import com.lc.apilc.services.UserDetailService;
import com.lc.apilc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value="/login")
public class LoginController {
    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public TokenResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            UserDetails userDetails = userDetailService.authenticate(loginRequest);
            User user = userService.findByLogin(userDetails.getUsername());
            String token = jwtService.createToken(user);
            return new TokenResponse(token);
        } catch (UsernameNotFoundException | LcException ex) {
            throw new LcException("Usuário inválido!", ErrorCodes.LOGIN_INVALIDO);
        }
    }

}
