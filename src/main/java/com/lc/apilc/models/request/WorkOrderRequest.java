package com.lc.apilc.models.request;

import com.lc.apilc.models.entity.Client;
import com.lc.apilc.models.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class WorkOrderRequest {
    @NotBlank
    private String status;

    @NotNull
    private Client client;

    @NotNull
    private User user;
}
