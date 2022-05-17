package com.lc.apilc.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WorkOrderStatus {
    @NotBlank(message = "Status não pode ser vazio!")
    private String status;
}
