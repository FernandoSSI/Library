package com.FernandoSSI.Library.services;

import com.FernandoSSI.Library.domain.Book;
import com.FernandoSSI.Library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repo;

    public List<Book> findAll(){
        return repo.findAll();
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

}
