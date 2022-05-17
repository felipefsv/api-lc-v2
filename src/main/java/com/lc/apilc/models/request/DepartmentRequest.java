package com.lc.apilc.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DepartmentRequest {
    @NotBlank(message = "Nome não pode ser vazio!")
    private String name;
}
