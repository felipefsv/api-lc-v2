package com.lc.apilc.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WorkOrderStatusRequest {
    @NotBlank(message = "{campo.statusos.status.obrigatorio}")
    private String status;
}
