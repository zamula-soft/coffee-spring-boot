package com.example.coffee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
public class InitDbController implements CommandLineRunner {
    //auto start after running app
    //usually used for initialization
    private static final Logger log = LoggerFactory.getLogger(CoffeeController.class);

    private CoffeeRepository coffeeRepository;

    @Autowired
    public InitDbController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Saving initial coffees");
        coffeeRepository.saveAll(
                Set.of(
                        new Coffee("Ristretto"),
                        new Coffee("Cappuccino"),
                        new Coffee("Espresso"),
                        new Coffee("Latte")
                )
        );

    }
}
