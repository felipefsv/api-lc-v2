package com.lc.apilc.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "{campo.login.login.obrigatorio}")
    private String login;
    @NotBlank(message = "{campo.login.senha.obrigatorio}")
    private String password;
}
