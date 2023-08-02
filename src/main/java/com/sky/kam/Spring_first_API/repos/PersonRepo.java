package com.sky.kam.Spring_first_API.repos;

import com.sky.kam.Spring_first_API.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<Person,Integer> {

    List<Person> findByFirstName(String firstName);

    @Query(value = "Select age from Person where first_Name= ?1", nativeQuery = true)
    Integer findAgeByFirstName(String firstName);

    List<Person> findByFirstNameIgnoreCase(String firstName);
}
