package com.FernandoSSI.Library.services;

import com.FernandoSSI.Library.domain.Book;
import com.FernandoSSI.Library.repositories.BookRepository;
import com.FernandoSSI.Library.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Book> findByTitle(String text, Pageable pageable){
        return repo.findByTitle(text, pageable);
    }

    public Page<Book> findByAuthor(String text, Pageable pageable){
        return repo.findByAuthor(text, pageable);
    }

    public Page<Book> find(String text, Pageable pageable){
        return repo.find(text, pageable);
    }

    public Book insert(Book book){
        Book existingBook = repo.findExactBook(book.getTitle(), book.getAuthor(), book.getCondition());
        if(existingBook == null){
            return repo.insert(book);
        } else {
            existingBook.setQuantity(existingBook.getQuantity()+book.getQuantity());
            return repo.save(existingBook);
        }

    }

    public void delete(String id){
        Book book = findById(id);
        if(book != null && book.getQuantity() == 0){
            repo.deleteById(id);
        } else if (book != null && book.getQuantity() > 0){
            book.setQuantity(book.getQuantity()-1);
            repo.save(book);
        }
    }

    public Book update(Book book){
        Book newBook = findById(book.getId());
        newBook.setTitle(book.getTitle());
        newBook.setAuthor(book.getAuthor());
        newBook.setPrice(book.getPrice());
        newBook.setCondition(book.getCondition());
        newBook.setCategory(book.getCategory());
        newBook.setImgUrl(book.getImgUrl());
        newBook.setQuantity(book.getQuantity());

        return repo.save(newBook);
    }



}
