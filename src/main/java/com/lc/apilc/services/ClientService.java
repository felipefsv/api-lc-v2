package com.lc.apilc.services;

import com.lc.apilc.models.entity.Client;
import com.lc.apilc.models.request.ClientRequest;
import com.lc.apilc.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(ClientRequest clientRequest) {
        Client client = new Client(clientRequest);
        return this.clientRepository.save(client);
    }

    public List<Client> getCLients(Example example) {
        return this.clientRepository.findAll(example);
    }

    public Optional<Client> findById(UUID id){
        return this.clientRepository.findById(id);
    }

    @Transactional
    public Client updateSupplier(Client client){
        return this.clientRepository.save(client);
    }
}
