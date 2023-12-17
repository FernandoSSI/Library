package com.FernandoSSI.Library.controller;

import com.FernandoSSI.Library.controller.util.URL;
import com.FernandoSSI.Library.domain.Book;
import com.FernandoSSI.Library.domain.Client;
import com.FernandoSSI.Library.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping(value = "/search")
    public ResponseEntity<Page<Client>> find(
            @RequestParam(required = false) String text,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        text = URL.decodeParam(text);

        Page<Client> list = service.find(text, pageable);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/namesearch")
    public ResponseEntity<Page<Client>> findByName(
            @RequestParam(required = false) String text,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        text = URL.decodeParam(text);

        Page<Client> list = service.findByName(text, pageable);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/citysearch")
    public ResponseEntity<Page<Client>> findByCity(
            @RequestParam(required = false) String text,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        text = URL.decodeParam(text);
        Page<Client> list = service.findByCity(text, pageable);

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
