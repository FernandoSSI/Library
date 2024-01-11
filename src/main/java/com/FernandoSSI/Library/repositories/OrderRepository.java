package com.FernandoSSI.Library.repositories;

import com.FernandoSSI.Library.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface OrderRepository extends MongoRepository<Order, String> {

    @Query("{ 'client.name': { $regex: ?0, $options: 'i'} }")
    Page<Order> findByClientName(String text, Pageable pageable);

    @Query("{ 'books.title': { $regex: ?0, $options: 'i'} }")
    Page<Order> findByTitle(String text, Pageable pageable);

    @Query("{ 'date': { $regex: ?0, $options: 'i'} }")
    Page<Order> findByDate(String text, Pageable pageable);

    @Query("{$or:[{ 'client.name': { $regex: ?0, $options: 'i'} }, { 'date': { $regex: ?0, $options: 'i'} }, { 'books.title': { $regex: ?0, $options: 'i'} } ]}")
    Page<Order> find(String text, Pageable pageable);
}
