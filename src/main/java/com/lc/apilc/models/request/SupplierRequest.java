package com.lc.apilc.models.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class SupplierRequest {
    @NotBlank(message = "Nome não pode ser vazio!")
    private String name;

    @NotBlank(message = "Endereço não pode ser vazio!")
    private String address;

    @NotBlank(message = "Telefone não pode ser vazio!")
    private String phone;

    @NotBlank(message = "Documento não pode ser vazio!")
    private String document;

    @NotBlank(message = "Email não pode ser vazio!")
    private String email;
}
