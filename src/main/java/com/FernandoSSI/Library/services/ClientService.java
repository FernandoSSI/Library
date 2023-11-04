package com.FernandoSSI.Library.services;

import com.FernandoSSI.Library.domain.Client;
import com.FernandoSSI.Library.repositories.ClientRepository;
import com.FernandoSSI.Library.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    public List<Client> findAll(){
        return repo.findAll();
    }

    public Client findById(String id){
        Optional<Client> client = repo.findById(id);
        return client.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Client> findByName(String name){
        return repo.findByName(name);
    }

    public List<Client> findByCity(String city){
        return repo.findByCity(city);
    }

    public Client insert(Client client){
        return repo.insert(client);
    }

    public void delete(String id){
        if(!(repo.findById(id) == null)){
            repo.deleteById(id);
        }
    }

    public Client update(Client client){
        Client newClient= findById(client.getId());
        newClient.setName(client.getName());
        newClient.setNumber(client.getNumber());
        newClient.setCity(client.getCity());
        newClient.setStreet(client.getStreet());
        newClient.setHn(client.getHn());
        newClient.setNbh(client.getNbh());

        return repo.save(newClient);
    }
}
