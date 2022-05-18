package com.lc.apilc.models.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class SupplierRequest {
    @NotBlank(message = "{campo.fornecedor.nome.obrigatorio}")
    private String name;

    @NotBlank(message = "{campo.fornecedor.endereco.obrigatorio}")
    private String address;

    @NotBlank(message = "{campo.fornecedor.telefone.obrigatorio}")
    private String phone;

    @NotBlank(message = "{campo.fornecedor.documento.obrigatorio}")
    private String document;

    @NotBlank(message = "{campo.fornecedor.email.obrigatorio}")
    private String email;
}
