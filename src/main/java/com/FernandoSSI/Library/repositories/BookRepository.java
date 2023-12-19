package com.FernandoSSI.Library.repositories;

import com.FernandoSSI.Library.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    @Query("{'title': { $regex: '^?0$', $options: 'i' }, 'author': { $regex: '^?1$', $options: 'i' }, 'condition': ?2}")
    Book findExactBook(String title, String author, String condition);

    @Query("{ 'title': { $regex: ?0, $options: 'i'} }")
    Page<Book> findByTitle(String text, Pageable pageable);

    @Query("{ 'author': { $regex: ?0, $options: 'i'} }")
    Page<Book> findByAuthor(String text, Pageable pageable);

    @Query("{$or:[{ 'author': { $regex: ?0, $options: 'i'} }, { 'title': { $regex: ?0, $options: 'i'} } ]}")
    Page<Book> find(String text, Pageable pageable);

}
