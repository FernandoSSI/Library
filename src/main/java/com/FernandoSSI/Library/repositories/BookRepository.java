package com.FernandoSSI.Library.repositories;

import com.FernandoSSI.Library.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    @Query("{ 'title': { $regex: ?0, $options: 'i'} }")
    List<Book> findByTitle(String text);

    @Query("{ 'author': { $regex: ?0, $options: 'i'} }")
    List<Book> findByAuthor(String text);

    @Query("{$or:[{ 'author': { $regex: ?0, $options: 'i'} }, { 'title': { $regex: ?0, $options: 'i'} } ]}")
    List<Book> find(String text);

}
