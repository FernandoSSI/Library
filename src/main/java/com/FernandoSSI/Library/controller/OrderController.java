package com.FernandoSSI.Library.controller;

import com.FernandoSSI.Library.controller.util.URL;
import com.FernandoSSI.Library.domain.Client;
import com.FernandoSSI.Library.domain.Order;
import com.FernandoSSI.Library.services.OrderService;
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
@RequestMapping(value = "/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public List<Order> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/searchclientname")
    public ResponseEntity<Page<Order>> findByClientName(
            @RequestParam(required = false) String text,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        text = URL.decodeParam(text);

        Page<Order> list = service.findByClientName(text, pageable);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/searchdate")
    public ResponseEntity<Page<Order>> findByDate(
            @RequestParam(required = false) String text,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        text = URL.decodeParam(text);

        Page<Order> list = service.findByDate(text, pageable);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Page<Order>> find(
            @RequestParam(required = false) String text,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        text = URL.decodeParam(text);

        Page<Order> list = service.find(text, pageable);

        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Order> insert( @RequestBody Order order){
        order = service.insert(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Order> update(@RequestBody Order order, @PathVariable String id){
        order.setId(id);
        service.update(order);
        return ResponseEntity.noContent().build();
    }




}
