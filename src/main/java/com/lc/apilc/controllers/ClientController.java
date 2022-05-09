package com.lc.apilc.controllers;

import com.lc.apilc.models.entity.Client;
import com.lc.apilc.models.request.ClientRequest;
import com.lc.apilc.models.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping()
    public List<Client> getClients() {
        return clientService.getCLients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object createClient(@RequestBody @Valid ClientRequest clientRequest) {
        return clientService.createClient(clientRequest);
    }

}
