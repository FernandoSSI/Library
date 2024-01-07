package com.FernandoSSI.Library.dto;

import com.FernandoSSI.Library.domain.Book;

import java.io.Serializable;
import java.util.Objects;

public class BookDTO implements Serializable {

    private String id;
    private String title;
    private String author;
    private String condition;
    private Double price;
    private Integer quantity;


    public BookDTO(){

    }

    public BookDTO(Book book, Integer quantity) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.price = book.getPrice();
        this.condition = book.getCondition();
        if(quantity < book.getQuantity()) {
            this.quantity = quantity;
        } else {
            this.quantity = book.getQuantity();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Double getTotalPrice(){
        return price * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(id, bookDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
