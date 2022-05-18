package com.lc.apilc.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ClientRequest {
    @NotBlank(message = "{campo.cliente.nome.obrigatorio}")
    private String name;

    @NotBlank(message = "{campo.cliente.endereco.obrigatorio}")
    private String address;

    @NotBlank(message = "{campo.cliente.telefone.obrigatorio}")
    private String phone;

    @NotBlank(message = "{campo.cliente.documento.obrigatorio}")
    private String document;

    @NotBlank(message = "{campo.cliente.email.obrigatorio}")
    private String email;
}
