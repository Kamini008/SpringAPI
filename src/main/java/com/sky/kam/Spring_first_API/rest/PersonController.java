package com.sky.kam.Spring_first_API.rest;
import com.sky.kam.Spring_first_API.domain.Person;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

public class PersonController {


    @RestController
    public class API {

        private List<Person> person=new ArrayList<>();

        @PostMapping("/create")
        public Person create(@RequestBody Person person) {
            System.out.println("RECEIVED: " + person);
            this.person.add(person);
            return this.person.get(this.person.size() - 1);
        }

        @GetMapping("/getAll")
        public List<Person> getAll() {
            System.out.println(this.person);
            return this.person;
        }



    }

}
