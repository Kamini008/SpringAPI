package com.sky.kam.Spring_first_API.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.kam.Spring_first_API.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.context.jdbc.Sql;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // loads the application context
@AutoConfigureMockMvc // create a MockMVC bean

@Sql(scripts = {"classpath:Person-schema.sql","classpath:Person-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

public class PersonMVCTest {

    @Autowired // tells spring to inject the MockMVC bean into this class
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreate() throws Exception {
        // METHOD: POST
        // PATH: /hedgehog/create
        // BODY: JSON
        // HEADERS: CONTENT-TYPE: application\json
        Person Kite = new Person("Sea", "Orange", 15);
        System.out.println("DATA: " + Kite);
        String sonicJSON = this.mapper.writeValueAsString(Kite);
        System.out.println("JSON: " + sonicJSON);
        RequestBuilder req = MockMvcRequestBuilders.post("/Person/create/").content(sonicJSON).contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();
        Person responseBody = new Person(2, "Sea", "Orange", 15);
        System.out.println("DATA: " + responseBody);
        String responseBodyJSON = this.mapper.writeValueAsString(responseBody);
        System.out.println("JSON: " + responseBodyJSON);
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(responseBodyJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testCreate2() throws Exception {
        String sonicJSON = this.mapper.writeValueAsString(new Person("Sea", "Orange", 15));
        String responseBodyJSON = this.mapper.writeValueAsString(new Person(2, "Sea", "Orange", 15));
        this.mvc.perform(
                        MockMvcRequestBuilders.
                                post("/Person/create/")
                                .content(sonicJSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(responseBodyJSON));

    }

    @Test
    void testRead() throws Exception {
        final int id = 1;
        String responseBody = this.mapper.writeValueAsString(new Person(id, "KAMINI", "SINGH", 12));
        this.mvc.perform(get("/Person/getByID/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    void testCreateMultiple() throws Exception{
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person(1,"re","er",12));
        persons.add(new Person(2,"rere","erre",15));
        this.mvc.perform(
                        MockMvcRequestBuilders.
                                post("/Person/createMultiple/")
                                .content(mapper.writeValueAsString(persons))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testReadAll() throws Exception{
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person(1,"KAMINI","SINGH",12));
        String responseBody = this.mapper.writeValueAsString(persons);
        this.mvc.perform(get("/Person/getAll/"))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }
//
    @Test
    void testReadName() throws Exception{
        final String name = "Kamini";
        this.mvc.perform(get("/Person/findByFirstName/" + name))
                .andExpect(status().isOk());
    };
    
    @Test
    void testUpdate() throws Exception {
        final int id = 1;
        String responseBody = this.mapper.writeValueAsString(new Person(id, "KAMINI", "SINGH", 12));
        this.mvc.perform(patch("/Person/update/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    void testDelete() throws Exception{
        final int id = 1;
        String responseBody = this.mapper.writeValueAsString(new Person(id, "KAMINI", "SINGH", 12));
        this.mvc.perform(delete("/Person/remove/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    // Nice to have but not required:

    @Test // try and GET an object that doesn't exist
    void testNotFound() throws Exception{
        final int id = 2;
        //String responseBody = this.mapper.writeValueAsString(new Person(id, "KAMINI", "SINGH", 12));
        this.mvc.perform(get("/Person/getByID/" + id)).andExpect(status().isNotFound());
    }
//
    @Test // try and create an object that doesn't pass validation and check that the error works
    void testFailCreate() throws Exception {
        String sonicJSON = this.mapper.writeValueAsString(new Person(null, null, null));
        //String responseBodyJSON = this.mapper.writeValueAsString(new Person(1, "Sea", "Orange", 15));
        this.mvc.perform(
                        MockMvcRequestBuilders.
                                post("/Person/create/")
                                .content(sonicJSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}
