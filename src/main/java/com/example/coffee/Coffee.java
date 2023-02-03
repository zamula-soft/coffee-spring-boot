package com.example.coffee;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity // create table
@Table(name="coffee_table")
public class Coffee {
    @Id //primary key
    private final String id;
    private String name;

    public Coffee() // for JPA we need default constructor
    {
        this("");
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
}
