package com.FernandoSSI.Library.repositories;

import com.FernandoSSI.Library.domain.Order;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    @Query("{ 'name': { $regex: ?0, $options: 'i'} }")
    List<Order> findByClientName(String text);
}
