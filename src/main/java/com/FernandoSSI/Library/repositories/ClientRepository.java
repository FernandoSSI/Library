package com.FernandoSSI.Library.repositories;

import com.FernandoSSI.Library.domain.Book;
import com.FernandoSSI.Library.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ClientRepository extends MongoRepository<Client, String> {

    @Query("{ 'name': { $regex: ?0, $options: 'i'} }")
    Page<Client> findByName(String text, Pageable pageable);

    @Query("{ 'city': { $regex: ?0, $options: 'i'} }")
    Page<Client> findByCity(String text, Pageable pageable);

    @Query("{$or:[{ 'name': { $regex: ?0, $options: 'i'} }, { 'city': { $regex: ?0, $options: 'i'} } ]}")
    Page<Client> find(String text, Pageable pageable);

}
