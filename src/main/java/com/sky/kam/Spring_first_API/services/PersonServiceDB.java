package com.sky.kam.Spring_first_API.services;


import com.sky.kam.Spring_first_API.domain.Person;
import com.sky.kam.Spring_first_API.exceptions.PersonNotFoundException;
import com.sky.kam.Spring_first_API.repos.PersonRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class PersonServiceDB implements PersonService {

    private PersonRepo repo;

    public PersonServiceDB(PersonRepo repo) {
        this.repo = repo;
    }


    @Override
    public Person create(Person person) {
        return this.repo.save(person);
    }

    @Override
    public List<Person> create(List<Person> newperson) {
        return this.repo.saveAll(newperson);
    }

    @Override
    public List<Person> getAll() {
        return this.repo.findAll();
    }

    @Override
    public Person getById(int id) {

        Person actualPerson = this.repo.findById(id).orElseThrow(() -> new PersonNotFoundException());
        return actualPerson;
    }

    @Override
    public Person update(int id, String firstName, String lastName, Integer age) {
        Person toUpdate = this.getById(id);

        if (firstName != null) toUpdate.setFirstName(firstName);
        if (lastName != null) toUpdate.setLastName(lastName);
        if (age != null) toUpdate.setAge(age);

        return this.repo.save(toUpdate);
    }

    @Override
    public Person remove(int id) {
        Person toDelete = this.getById(id);
        this.repo.deleteById(id);
        return toDelete;
    }


    @Override
    public List<Person> findByFirstName(String firstName) {
        return this.repo.findByFirstName(firstName);
    }

    @Override
    public Integer findAgeByFirstName(String firstName) {
        return this.repo.findAgeByFirstName(firstName);
    }

    @Override
    public List<Person> findByFirstNameIgnoreCase(String firstName) {
        return this.repo.findByFirstNameIgnoreCase(firstName);
    }
}
