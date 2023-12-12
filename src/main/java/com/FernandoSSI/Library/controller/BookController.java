package com.FernandoSSI.Library.controller;

import com.FernandoSSI.Library.controller.util.URL;
import com.FernandoSSI.Library.domain.Book;
import com.FernandoSSI.Library.repositories.BookRepository;
import com.FernandoSSI.Library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private MongoTemplate mongoTemplate;


    @GetMapping
    public List<Book> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<Page<Book>> findByTitle(
            @RequestParam(required = false) String text,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);

        text = URL.decodeParam(text);
        Page<Book> list = service.findByTitle(text, pageable);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/authorsearch")
    public ResponseEntity<Page<Book>> findByAuthor(
            @RequestParam(required = false) String text,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);

        text = URL.decodeParam(text);
        Page<Book> list = service.findByAuthor(text, pageable);

        return ResponseEntity.ok().body(list);
    }


    @GetMapping(value = "/search")
    public ResponseEntity<Page<Book>> find(
            @RequestParam(required = false) String text,
            @RequestParam(defaultValue= "0") int page,
            @RequestParam(defaultValue= "10") int size){
        Pageable pageable = PageRequest.of(page, size);

        text = URL.decodeParam(text);
        Page<Book> list= service.find(text, pageable);

        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Book book){
        book = service.insert(book);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Book> update(@RequestBody Book book, @PathVariable String id){
        book.setId(id);
        service.update(book);
        return ResponseEntity.noContent().build();
    }


}
