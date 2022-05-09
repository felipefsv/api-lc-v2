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
    ClientService clientService;

    @GetMapping()
    public ResponseEntity<List<Client>> getClients() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getCLients());
    }

    @PostMapping
    public ResponseEntity<Object> createClient(@RequestBody @Valid ClientRequest clientRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(
                new Client(
                        clientRequest.getName(),
                        clientRequest.getDocument(),
                        clientRequest.getPhone(),
                        clientRequest.getEmail(),
                        clientRequest.getAddress()
                )));
    }

}
