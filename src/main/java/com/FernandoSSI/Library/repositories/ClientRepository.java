package com.FernandoSSI.Library.repositories;

import com.FernandoSSI.Library.domain.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ClientRepository extends MongoRepository<Client, String> {

    @Query("{ 'name': { $regex: ?0, $options: 'i'} }")
    List<Client> findByName(String text);

    @Query("{ 'city': { $regex: ?0, $options: 'i'} }")
    List<Client> findByCity(String text);

}
