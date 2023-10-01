package com.FernandoSSI.Library.services;

import com.FernandoSSI.Library.domain.Book;
import com.FernandoSSI.Library.repositories.BookRepository;
import com.FernandoSSI.Library.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class BookService {

    @Autowired
    private BookRepository repo;

    public List<Book> findAll(){
        return repo.findAll();
    }

    public Book findById(String id){
        Optional<Book> book= repo.findById(id);
        return book.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Book> findByTitle(String text){
        return repo.findByTitle(text);
    }

    public List<Book> findByAuthor(String text){
        return repo.findByAuthor(text);
    }

    public List<Book> find(String text){
        return repo.find(text);
    }

    public Book insert(Book book){
        return repo.insert(book);
    }

    public void delete(String id){
        if(!(repo.findById(id) == null)){
            repo.deleteById(id);
        }
    }

    public Book update(Book book){
        Book newBook = findById(book.getId());
        newBook.setTitle(book.getTitle());
        newBook.setAuthor(book.getAuthor());
        newBook.setPrice(book.getPrice());
        newBook.setCondition(book.getCondition());

        return repo.save(newBook);
    }





}
