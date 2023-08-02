package com.sky.kam.Spring_first_API.services;

import com.sky.kam.Spring_first_API.domain.Person;

import java.util.List;

public interface PersonService {

    Person create(Person person);

    List<Person> create(List<Person> newperson);


    List<Person> getAll();


    Person getById(int id);


    Person update(int id, String firstName, String lastName, Integer age);

    Person remove(int id);


    List<Person> findByFirstName(String firstName);

    Integer findAgeByFirstName(String firstName);
    List<Person> findByFirstNameIgnoreCase(String firstName);
}
