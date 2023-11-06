package com.FernandoSSI.Library.repositories;

import com.FernandoSSI.Library.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    @Query("{ 'title': { $regex: ?0, $options: 'i'} }")
    List<Book> findByTitle(String text);

    @Query("{'title': { $regex: '^?0$', $options: 'i' }, 'author': { $regex: '^?1$', $options: 'i' }, 'condition': ?2}")
    Book findExactBook(String title, String author, String condition);

    @Query("{ 'author': { $regex: ?0, $options: 'i'} }")
    List<Book> findByAuthor(String text);

    @Query("{$or:[{ 'author': { $regex: ?0, $options: 'i'} }, { 'title': { $regex: ?0, $options: 'i'} } ]}")
    List<Book> find(String text);

}
