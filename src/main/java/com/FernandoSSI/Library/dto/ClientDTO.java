package com.FernandoSSI.Library.dto;

import com.FernandoSSI.Library.domain.Client;

import java.io.Serializable;
import java.util.Objects;

public class ClientDTO implements Serializable {

    private String id;
    private String name;
    private Long number;

    public ClientDTO(){

    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.number = client.getNumber();
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
