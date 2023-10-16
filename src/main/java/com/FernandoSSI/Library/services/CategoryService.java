package com.FernandoSSI.Library.services;

import com.FernandoSSI.Library.domain.Book;
import com.FernandoSSI.Library.domain.Category;
import com.FernandoSSI.Library.repositories.CategoryRepository;
import com.FernandoSSI.Library.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService{

    @Autowired
    CategoryRepository repo;

    public List<Category> findAll(){
        return repo.findAll();
    }

    public Category findById(String id){
        Optional<Category> category= repo.findById(id);
        return category.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Category> findByName(String text){
        return repo.findByName(text);
    }

    public Category insert(Category category){
        return repo.insert(category);
    }

    public void delete(String id){
        if(!(repo.findById(id) == null)){
            repo.deleteById(id);
        }
    }

    public Category update(Category category){
        Category newCategory = findById(category.getId());
        newCategory.setCategoryName(category.getCategoryName());


        return repo.save(newCategory);
    }

}
