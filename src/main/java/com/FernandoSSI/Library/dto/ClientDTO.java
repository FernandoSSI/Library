package com.FernandoSSI.Library.dto;

import com.FernandoSSI.Library.domain.Client;

import java.io.Serializable;
import java.util.Objects;

public class ClientDTO implements Serializable {

    private String id;
    private String name;
    private Long number;
    private String city;
    private String street;
    private String nbh;
    private Integer hn;

    public ClientDTO(){

    }

    public ClientDTO(String id, String name, Long number, String city, String street, String nbh, Integer hn) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.city = city;
        this.street = street;
        this.nbh = nbh;
        this.hn = hn;
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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
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

    public String getNbh() {
        return nbh;
    }

    public void setNbh(String nbh) {
        this.nbh = nbh;
    }

    public Integer getHn() {
        return hn;
    }

    public void setHn(Integer hn) {
        this.hn = hn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDTO clientDTO = (ClientDTO) o;
        return Objects.equals(id, clientDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
