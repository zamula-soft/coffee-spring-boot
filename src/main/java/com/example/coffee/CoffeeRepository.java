package com.example.coffee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends CrudRepository<Coffee, String> {
    //Coffee - our class through repository will be saved
    //String type of the key for this class
}
