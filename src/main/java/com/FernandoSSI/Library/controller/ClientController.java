package com.FernandoSSI.Library.controller;

import com.FernandoSSI.Library.controller.util.URL;
import com.FernandoSSI.Library.domain.Book;
import com.FernandoSSI.Library.domain.Client;
import com.FernandoSSI.Library.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public List<Client> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/namesearch")
    public ResponseEntity<List<Client>> findByName(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        List<Client> list = service.findByName(text);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/citysearch")
    public ResponseEntity<List<Client>> findByCity(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        List<Client> list = service.findByCity(text);

        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Client> insert(@RequestBody Client client){
        client = service.insert(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Client> update(@RequestBody Client client, @PathVariable String id){
        client.setId(id);
        service.update(client);
        return ResponseEntity.noContent().build();
    }











}
