package com.lc.apilc.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ClientRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;

    @NotBlank
    private String document;
}
