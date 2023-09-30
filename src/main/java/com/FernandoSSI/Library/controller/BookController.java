package com.FernandoSSI.Library.controller;

import com.FernandoSSI.Library.controller.util.URL;
import com.FernandoSSI.Library.domain.Book;
import com.FernandoSSI.Library.repositories.BookRepository;
import com.FernandoSSI.Library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public List<Book> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Book>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        List<Book> list = service.findByTitle(text);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/authorsearch")
    public ResponseEntity<List<Book>> findByAuthor(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        List<Book> list = service.findByAuthor(text);

        return ResponseEntity.ok().body(list);
    }



}
