package com.lc.apilc.models.request;

import com.lc.apilc.models.entity.Client;
import com.lc.apilc.models.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WorkOrderRequest {
    @NotBlank
    private String status;

    @NotBlank
    private Client client;

    @NotBlank
    private User user;
}
