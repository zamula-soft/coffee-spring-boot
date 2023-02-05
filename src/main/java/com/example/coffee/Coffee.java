package com.example.coffee;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity // create table
@Table(name="coffee_table")
public class Coffee {
    @Id //primary key
    private String id;
    @NotBlank(message = "Name is mandatory")
    private String name;

    public Coffee() // for JPA we need default constructor
    {
        this("");
    }

    public void setId(String id) {
        this.id = id;
    }

    public Coffee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Coffee(String name){
        this(
                UUID.randomUUID().toString(),
                name
        );
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
