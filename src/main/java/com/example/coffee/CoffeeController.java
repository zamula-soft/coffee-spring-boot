package com.example.coffee;


import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController -> Controller let use template
@Controller
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


////    @GetMapping("/coffees")
//    @GetMapping
//    public Iterable<Coffee> getCoffees()
//    {
//
////        return coffees;
//        return coffeeRepository.findAll();
//    }

    @GetMapping
    public String getAllCoffees(Model model){ //model lets bind instance into template
        Iterable<Coffee> all  = coffeeRepository.findAll();
        model.addAttribute("coffees", all);
        log.info(all.toString());
        return "list";
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



@GetMapping("/add")
    public String addCoffee(Coffee coffee){
    return "add-coffee";
}

////    @DeleteMapping("/coffees/{id}")
//@DeleteMapping("/{id}")
//public void deleteCoffeeById(
//            @PathVariable(name = "id") String id)
//    {
//
////        coffees.removeIf(coffee -> coffee.getId().equals(id));
//        coffeeRepository.deleteById(id);
//
//    }


    //new delete
    @GetMapping("/delete/{id}")
    public String deleteCoffeeById(@PathVariable(name = "id") String id){
        log.info("Deleting coffee id: "+id);
        coffeeRepository.deleteById(id);
        return "redirect:/coffees";
    }


    @GetMapping("/edit/{id}")
    public String updateCoffee(@PathVariable(name = "id") String id, Model model){
        Coffee coffee = coffeeRepository.findById(id).get();
        model.addAttribute("coffee", coffee);
        return "update-coffee";
    }

@PostMapping("/update/{id}")
public String updateCoffeeModel(@PathVariable(name = "id") String id,
                                @Valid Coffee coffee, //validate
                                BindingResult result,
                                Model model)
{//result of validation
        log.info(coffee.toString());
        if (result.hasErrors())
        {
            coffee.setId(id);
            return "update-coffee";
        }
        coffeeRepository.save(coffee);
        return "redirect:/coffees";
}


////    @PostMapping("/coffees")
//@PostMapping
//    public Coffee postCoffee(@RequestBody Coffee coffee){
////        coffees.add(coffee);
////        return coffee;
//    if (coffee != null
//    && coffee.getId() != null
//    && !coffee.getId().isEmpty()
//    && coffee.getName() != null
//    && !coffee.getName().isEmpty())
//        coffeeRepository.save(coffee);
//    return coffee;
//
//    }

 @PostMapping
 public String addNewCoffee(@Valid Coffee coffee,
                            BindingResult result,
                            Model model){
        if (result.hasErrors())
        {
            return "add-coffee";
        }
        coffeeRepository.save(coffee);
        return "redirect:/coffees";
 }


 @GetMapping("/search")
 public String searchCoffee(@PathParam("name") String name, Model model){
        List<Coffee> coffeesByName = coffeeRepository.findByNameContaining(name);
        model.addAttribute("coffees", coffeesByName);
        log.info(coffeesByName.toString());
        return "list";

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


//    @PatchMapping("/{name}")
//    public List<Coffee> getCoffeeByName( //optional- > list
//            @PathVariable(name = "name") String name)
//    {
//
//        return coffeeRepository.findByNameContaining(name);
////                coffees.stream()
////                        .filter(coffee -> coffee.getName().equals(name))
////                        .findFirst();
//
////        for (Coffee c: coffees) {
////            if (c.getName().equals(name))
////                return Optional.of(c);
////        }
////        return Optional.empty();
//    }




}


