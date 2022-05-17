package com.lc.apilc.models.request;

import com.lc.apilc.models.entity.Client;
import com.lc.apilc.models.entity.User;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class WorkOrderRequest {
    @NotNull(message = "Cliente não pode ser null!")
    private Client client;

    @NotNull(message = "Usuário não pode ser null!")
    private User user;
}
