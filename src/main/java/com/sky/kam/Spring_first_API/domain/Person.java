package com.sky.kam.Spring_first_API.domain;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

public class Person {

    String FirstName;

    String LastName;


    Integer Age;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public Person() {

    }
    public Person(String firstName, String lastName, Integer age) {
        FirstName = firstName;
        LastName = lastName;
        Age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Age=" + Age +
                '}';
    }


}
