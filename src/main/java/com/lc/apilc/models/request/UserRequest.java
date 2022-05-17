package com.lc.apilc.models.request;

import com.lc.apilc.models.entity.Department;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UserRequest {
    @NotBlank(message = "Nome não pode ser vazio!")
    private String name;

    @NotBlank(message = "Login não pode ser vazio!")
    private String login;

    @NotBlank(message = "Senha não pode ser vazio!")
    private String password;

    @NotNull(message = "Flag isAdmin não pode ser null!")
    private boolean isAdmin;

    @NotNull(message = "Departamento não pode ser null!")
    private Department department;
}
