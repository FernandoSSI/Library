package com.FernandoSSI.Library.repositories;

import com.FernandoSSI.Library.domain.Book;
import com.FernandoSSI.Library.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    @Query("{ 'categoryName': { $regex: ?0, $options: 'i'} }")
    List<Category> findByName(String text);
}
