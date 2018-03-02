package com.arun.springjdbcwithcache.controller;

import com.arun.springjdbcwithcache.dao.PersonDAO;
import com.arun.springjdbcwithcache.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonDAO personDAO;

    @GetMapping("/person/{id}")
    public List<Person> findById(@PathVariable final int id) {
        return personDAO.findById(id);
    }

    @PostMapping("/person/update")
    public String updateANewPerson(@RequestBody Person person) {
        int result = personDAO.updateById(person);
        return result == 1 ? "Successfully updated" : "Not able to update";
    }
}
