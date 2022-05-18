package com.lc.apilc.models.request;

import com.lc.apilc.models.entity.Client;
import com.lc.apilc.models.entity.User;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class WorkOrderRequest {
    @NotNull(message = "{campo.os.cliente.obrigatorio}")
    private Client client;

    @NotNull(message = "{campo.os.usuario.obrigatorio}")
    private User user;
}
