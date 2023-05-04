package com.devsu.challenge.repository;

import com.devsu.challenge.model.Client;
import com.devsu.challenge.repository.crud.ClientCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {

    @Autowired
    private ClientCrudRepository clientCrudRepository;

    public Optional<List<Client>> findAll(){
        return Optional.of(clientCrudRepository.findAll());
    }

    public Optional<Client> findById(Integer id){
        return clientCrudRepository.findById(id);
    }

    public Optional<Client> findByDni(String dni){
        return clientCrudRepository.findByDni(dni);
    }

    public Client save(Client client){
        return clientCrudRepository.save(client);
    }

    public void deleteById(Integer id){
        clientCrudRepository.deleteById(id);
    }

    public void deleteByDni(String dni){
        clientCrudRepository.deleteByDni(dni);
    }

}
