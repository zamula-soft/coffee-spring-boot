package com.example.coffee;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

//    public List<Coffee> coffees = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(CoffeeController.class);

    @Autowired //auto added
    CoffeeRepository coffeeRepository;

    public CoffeeController()
    {
//        coffees.addAll(
//                List.of(
//                        new Coffee("Espresso"),
//                        new Coffee("Latte"),
//                        new Coffee("Cappuccino"),
//                        new Coffee("Ristretto"),
//                        new Coffee("Macciato")
//                )
//        );

//        coffeeRepository.saveAll(
//                List.of(
//                new Coffee("Espresso"),
//                new Coffee("Latte"),
//                new Coffee("Cappuccino"),
//                new Coffee("Ristretto"),
//                new Coffee("Macciato")
//                )
//        );
    }


//    @GetMapping("/coffees")
    @GetMapping
    public Iterable<Coffee> getCoffees()
    {

//        return coffees;
        return coffeeRepository.findAll();
    }

//    @GetMapping("/coffees/{id}")
@GetMapping("/{id}")
    public Optional<Coffee> getCoffeeById(
            @PathVariable(name = "id") String id)
    {

//        for (Coffee c: coffees) {
//            if (c.getId().equals(id))
//                return Optional.of(c);
//        }
//        return Optional.empty();
        return coffeeRepository.findById(id);
    }



//    @DeleteMapping("/coffees/{id}")
@DeleteMapping("/{id}")
public void deleteCoffeeById(
            @PathVariable(name = "id") String id)
    {

//        coffees.removeIf(coffee -> coffee.getId().equals(id));
        coffeeRepository.deleteById(id);

    }

//    @PostMapping("/coffees")
@PostMapping
    public Coffee postCoffee(@RequestBody Coffee coffee){
//        coffees.add(coffee);
//        return coffee;
    if (coffee != null
    && coffee.getId() != null
    && !coffee.getId().isEmpty()
    && coffee.getName() != null
    && !coffee.getName().isEmpty())
        coffeeRepository.save(coffee);
    return coffee;

    }

/*
//    @PatchMapping("/coffees/{id}")

@PatchMapping("/{id}")
public Coffee patchCoffee(
            @PathVariable(name = "id") String id,
            @RequestBody Coffee coffee)
    {
        int coffeeIndex = -1;
        for (Coffee c: coffees)
        {
           if (c.getId().equals(id))
           {
               coffeeIndex = coffees.indexOf(c);
               coffees.set(coffeeIndex, c);
           }
        }
        return (coffeeIndex == -1) ? postCoffee(coffee) : coffee;
    }
*/
    @PutMapping("/{id}")
    public ResponseEntity<Coffee> putCoffee(
            @PathVariable(name = "id") String id,
            @RequestBody Coffee coffee)
    {
        log.info("PUT " + coffee.getId() + " | " + coffee.getName());
/*
        int coffeeIndex = -1;
        for (Coffee c: coffees)
        {
            if (c.getId().equals(id))
            {
                log.info("find " + coffee.getId() + " | " + coffee.getName());
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }
        return (coffeeIndex == -1) ?
                new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED) :
                new ResponseEntity<>(coffee, HttpStatus.OK);
 */
        if (coffeeRepository.existsById(id))
        {
            coffeeRepository.save(coffee);
            return new ResponseEntity<>(coffee, HttpStatus.OK);
        }
        else
        {
            coffeeRepository.save(coffee);
            return new ResponseEntity<>(coffee, HttpStatus.CREATED);
        }
    }

/*
    @PatchMapping("/{name}")
    public Optional<Coffee> getCoffeeByName(
            @PathVariable(name = "name") String name)
    {

        return
                coffees.stream()
                        .filter(coffee -> coffee.getName().equals(name))
                        .findFirst();

//        for (Coffee c: coffees) {
//            if (c.getName().equals(name))
//                return Optional.of(c);
//        }
//        return Optional.empty();
    }
*/



}


