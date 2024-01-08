package com.FernandoSSI.Library.services;

import com.FernandoSSI.Library.domain.Client;
import com.FernandoSSI.Library.repositories.ClientRepository;
import com.FernandoSSI.Library.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Client> findByName(String name, Pageable pageable){
        return repo.findByName(name, pageable);
    }

    public Page<Client> findByCity(String city, Pageable pageable){
        return repo.findByCity(city, pageable);
    }

    public Page<Client> find(String text, Pageable pageable){
        return repo.find(text, pageable);
    }

    public Client insert(Client client){
        return repo.insert(client);
    }

    public void delete(String id){
        if(repo.findById(id) != null){
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
