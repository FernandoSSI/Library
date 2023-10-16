package com.FernandoSSI.Library.controller;

import com.FernandoSSI.Library.controller.util.URL;
import com.FernandoSSI.Library.domain.Book;
import com.FernandoSSI.Library.domain.Category;
import com.FernandoSSI.Library.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    CategoryService service;

    @GetMapping
    public List<Category> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Category>> find(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        List<Category> list = service.findByName(text);

        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Category> insert (@RequestBody Category category){
        category = service.insert(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Category> delete (@RequestParam String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable String id){
        category.setId(id);
        service.update(category);
        return ResponseEntity.noContent().build();
    }



}
