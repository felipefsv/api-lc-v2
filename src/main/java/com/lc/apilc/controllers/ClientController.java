package com.lc.apilc.controllers;

import com.lc.apilc.models.entity.Client;
import com.lc.apilc.models.request.ClientRequest;
import com.lc.apilc.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping()
    public List<Client> findClientes(Client client) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Client> example = Example.of(client, matcher);
        return clientService.getCLients(example);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object createClient(@RequestBody @Valid ClientRequest clientRequest) {
        return clientService.createClient(clientRequest);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> udpateClient(@PathVariable UUID id, @RequestBody Client client) {
        return clientService
                .findById(id)
                .map(s -> {
                    client.setId(s.getId());
                    clientService.updateSupplier(client);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
