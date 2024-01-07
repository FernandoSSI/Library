package com.FernandoSSI.Library.controller;

import com.FernandoSSI.Library.controller.util.URL;
import com.FernandoSSI.Library.domain.Order;
import com.FernandoSSI.Library.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Order>> findByClientName(@RequestParam String text){
        text = URL.decodeParam(text);
        List<Order> list = service.findByClientName(text);

        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Order> insert( @RequestBody Order order){
        order = service.insert(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }




}
