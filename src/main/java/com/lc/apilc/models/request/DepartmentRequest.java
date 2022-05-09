package com.lc.apilc.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DepartmentRequest {
    @NotBlank
    private String name;
}
