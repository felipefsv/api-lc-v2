package com.lc.apilc.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DepartmentRequest {
    @NotBlank(message = "{campo.departamento.nome.obrigatorio}")
    private String name;
}
