//package org.example.peoplemanager.service;
//
//import org.example.peoplemanager.model.Person;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class PersonService {
//
//    private final List<Person> people = new ArrayList<>();
//
//    public PersonService() {
//        people.add(new Person(
//                1L,
//                "John",
//                "Smith",
//                "john.smith@upb.ro",
//                "1241EA",
//                List.of("Java", "Spring Boot", "PostgreSQL")
//        ));
//
//        people.add(new Person(
//                2L,
//                "Ana",
//                "Popescu",
//                "ana.popescu@upb.ro",
//                "1241EA",
//                List.of("React", "JavaScript", "CSS")
//        ));
//
//        people.add(new Person(
//                3L,
//                "Alex",
//                "Ionescu",
//                "alex.ionescu@upb.ro",
//                "1241EB",
//                List.of("Java", "React", "Git")
//        ));
//
//        people.add(new Person(
//                4L,
//                "Maria",
//                "Georgescu",
//                "maria.georgescu@upb.ro",
//                "1241EC",
//                List.of("PostgreSQL", "SQL", "Database Design")
//        ));
//    }
//
//    public List<Person> getAllPeople() {
//        return people;
//    }
//
//    public List<Person> searchPeople(String query) {
//        String search = query.toLowerCase().trim();
//        return people.stream()
//                .filter(person -> {
//                    boolean matchesName = person.getFirstName().toLowerCase().contains(search) || person.getLastName().toLowerCase().contains(search);
//                    boolean matchesGroup = person.getGroup().toLowerCase().contains(search);
//                    boolean matchesSkill = person.getSkills().stream().anyMatch(skill -> skill.equalsIgnoreCase(query.trim()));
//                    return matchesName || matchesGroup || matchesSkill;
//                })
//                .toList();
//    }
//}

package org.example.peoplemanager.service;

import org.example.peoplemanager.model.Person;
import org.example.peoplemanager.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Person not found")
                );
    }

    public Person createPerson(Person person) {
        person.setId(null);
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person updatedPerson) {

        Person person = getPersonById(id);

        person.setFirstName(updatedPerson.getFirstName());
        person.setLastName(updatedPerson.getLastName());
        person.setEmail(updatedPerson.getEmail());
        person.setGroupName(updatedPerson.getGroupName());
        person.setSkills(updatedPerson.getSkills());

        return personRepository.save(person);
    }

    public void deletePerson(Long id) {

        Person person = getPersonById(id);

        personRepository.delete(person);
    }

    public List<Person> searchPeople(String query) {

        String search = query.toLowerCase().trim();

        return personRepository.findAll()
                .stream()
                .filter(person -> {

                    boolean matchesName =
                            person.getFirstName()
                                    .toLowerCase()
                                    .contains(search)
                                    ||
                                    person.getLastName()
                                            .toLowerCase()
                                            .contains(search);

                    boolean matchesGroup =
                            person.getGroupName()
                                    .toLowerCase()
                                    .contains(search);

                    boolean matchesSkill =
                            person.getSkills()
                                    .stream()
                                    .anyMatch(skill ->
                                            skill.equalsIgnoreCase(query.trim())
                                    );

                    return matchesName
                            || matchesGroup
                            || matchesSkill;
                })
                .toList();
    }
}