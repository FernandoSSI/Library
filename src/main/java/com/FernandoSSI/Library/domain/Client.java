package com.FernandoSSI.Library.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Document( collection = "client")
public class Client implements Serializable {

    @Id
    private String id;
    private String name;
    private Integer number;
    private String city;
    private String street;
    private Integer hn;
    private String nbh;


    public Client(){

    }

    public Client(String id, String name, Integer number, String city, String street, Integer hn, String nbh) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.city = city;
        this.street = street;
        this.hn = hn;
        this.nbh = nbh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHn() {
        return hn;
    }

    public void setHn(Integer hn) {
        this.hn = hn;
    }

    public String getNbh() {
        return nbh;
    }

    public void setNbh(String nbh) {
        this.nbh = nbh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
