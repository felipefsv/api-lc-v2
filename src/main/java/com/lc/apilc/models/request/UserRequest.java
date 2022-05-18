package com.lc.apilc.models.request;

import com.lc.apilc.models.entity.Department;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UserRequest {
    @NotBlank(message = "{campo.usuario.nome.obrigatorio}")
    private String name;

    @NotBlank(message = "{campo.usuario.login.obrigatorio}")
    private String login;

    @NotBlank(message = "{campo.usuario.senha.obrigatorio}")
    private String password;

    @NotNull(message = "{campo.usuario.isAdm.obrigatorio}")
    private boolean isAdmin;

    @NotNull(message = "{campo.usuario.departamento.obrigatorio}")
    private Department department;
}
