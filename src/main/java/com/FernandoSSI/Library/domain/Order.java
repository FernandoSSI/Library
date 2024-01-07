package com.FernandoSSI.Library.domain;

import com.FernandoSSI.Library.dto.BookDTO;
import com.FernandoSSI.Library.dto.ClientDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Document( collection = "order")
public class Order implements Serializable {

    @Id
    private String id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private ClientDTO client;
    private List<BookDTO> books;
    private Double totalPrice;

    public Order(){

    }

    public Order(String id, LocalDate date, ClientDTO client, List<BookDTO> books, Double totalPrice) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.books = books;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
