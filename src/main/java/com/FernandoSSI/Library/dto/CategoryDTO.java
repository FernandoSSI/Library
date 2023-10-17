package com.FernandoSSI.Library.dto;

import com.FernandoSSI.Library.domain.Category;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private String id;
    private String categoryName;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
