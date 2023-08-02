package com.sky.kam.Spring_first_API.services;
import com.sky.kam.Spring_first_API.domain.Person;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class PersonServiceList implements PersonService {

    private List<Person> persons = new ArrayList<>();

    @Override
    public Person create(Person person) {
        this.persons.add(person);
        Person created = this.persons.get(this.persons.size() - 1);
        return created;
    }

    @Override
    public List<Person> create(List<Person> newperson) {
        if (this.persons.addAll(newperson)) {
            return newperson;
        } else {
            return null;
        }
    }

    @Override
    public List<Person> getAll() {
        return this.persons;
    }

    @Override
    public Person getById(int id) {
        Person found = this.persons.get(id);
        return found;
    }

    @Override
    public Person update(int id, String name, String colour, Integer age) {
        Person toUpdate = this.persons.get(id);
        return toUpdate;
    }

    @Override
    public Person remove(int id) {
        return this.persons.remove(id);
    }

    @Override
    public List<Person> findByFirstName(String name) {
        return null;
    }

    @Override
    public Integer findAgeByFirstName(String name) {
        return null;
    }


    @Override
    public List<Person> findByFirstNameIgnoreCase(String name) {
        return null;
    }
}
