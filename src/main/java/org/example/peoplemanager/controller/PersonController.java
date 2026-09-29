//package org.example.peoplemanager.controller;
//
//import org.example.peoplemanager.model.Person;
//import org.example.peoplemanager.service.PersonService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/people")
//@CrossOrigin(origins = "http://localhost:5173")
//public class PersonController {
//
//    private final PersonService personService;
//
//    public PersonController(PersonService personService) {
//        this.personService = personService;
//    }
//
//    @GetMapping
//    public List<Person> getAllPeople() {
//        return personService.getAllPeople();
//    }
//
//    @GetMapping("/search")
//    public List<Person> searchPeople(@RequestParam String q) {
//        return personService.searchPeople(q);
//    }
//}

package org.example.peoplemanager.controller;

import org.example.peoplemanager.model.Person;
import org.example.peoplemanager.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
@CrossOrigin(origins = "http://localhost:5173")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }


    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }


    @GetMapping("/search")
    public List<Person> searchPeople(@RequestParam String q) {
        return personService.searchPeople(q);
    }


    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }


    @PutMapping("/{id}")
    public Person updatePerson(
            @PathVariable Long id,
            @RequestBody Person person
    ) {
        return personService.updatePerson(id, person);
    }


    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}