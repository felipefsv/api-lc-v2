package com.lc.apilc.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "Login não pode ser vazio!")
    private String login;
    @NotBlank(message = "Senha não pode ser vazio!")
    private String password;
}
