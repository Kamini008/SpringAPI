package com.sky.kam.Spring_first_API.rest;
import com.sky.kam.Spring_first_API.domain.Person;
import com.sky.kam.Spring_first_API.services.PersonService;
import com.sky.kam.Spring_first_API.services.PersonServiceList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Person")

public class PersonController {

        private PersonService service;
        public PersonController(PersonService service) {
        this.service = service;
    }

        @PostMapping("/create")
         public ResponseEntity<Person> create(@RequestBody Person person) {
            return new ResponseEntity<>(this.service.create(person), HttpStatus.CREATED);
         }


         @PostMapping("/createMultiple")
         public ResponseEntity<List<Person>> create(@RequestBody List<Person> newpersons) {
         System.out.println("RECEIVED: " + newpersons);
            if (this.service.create(newpersons) != null) {
            return new ResponseEntity<>(newpersons, HttpStatus.CREATED);
             } else {
            return ResponseEntity.internalServerError().build();
        }
        }

         @GetMapping("/getAll")
         public List<Person> getAll() {
            return this.service.getAll();
    }

          @GetMapping("/getByID/{id}")
          public Person getById(@PathVariable int id) {
            return this.service.getById(id);
    }

        @DeleteMapping("/remove/{id}")
        public Person remove(@PathVariable int id) {
        return this.service.remove(id);
        }


      @PatchMapping("/update/{id}")
      public Person update(@PathVariable int id, @RequestParam(value = "firstName", required = false) String firstName, @RequestParam(value = "lastname", required = false) String lastName, @RequestParam(value = "age", required = false) Integer age) {
            return this.service.update(id, firstName, lastName, age);

    }

    @GetMapping("/findByFirstName/{firstName}")
    public List<Person> findByFirstName(@PathVariable String firstName) {
        return this.service.findByFirstName(firstName);
    }

    @GetMapping("/findAgeByFirstName/{firstName}")
    public Integer findAgeByFirstName(@PathVariable String firstName) {
        return this.service.findAgeByFirstName(firstName);
    }

    @GetMapping("/findAgeByFirstNameIgnoreCase/{firstName}")
    public List<Person> findByFirstNameIgnoreCase(@PathVariable String firstName) {
        return this.service.findByFirstNameIgnoreCase(firstName);
    }
}

