package com.lc.apilc.models.services;

import com.lc.apilc.models.entity.Client;
import com.lc.apilc.models.request.ClientRequest;
import com.lc.apilc.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(ClientRequest clientRequest) {
        Client client = new Client(clientRequest);
        return this.clientRepository.save(client);
    }

    public List<Client> getCLients() {
        return this.clientRepository.findAll();
    }
}
