package org.example.peoplemanager.config;

import org.example.peoplemanager.model.Person;
import org.example.peoplemanager.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDataInitializer implements CommandLineRunner {

    private final PersonRepository personRepository;

    public PersonDataInitializer(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) {
        saveIfMissing(new Person(
                null,
                "John",
                "Smith",
                "john.smith@upb.ro",
                "1241EA",
                List.of("Java", "Spring Boot", "PostgreSQL")
        ));

        saveIfMissing(new Person(
                null,
                "Ana",
                "Popescu",
                "ana.popescu@upb.ro",
                "1241EA",
                List.of("React", "JavaScript", "CSS")
        ));

        saveIfMissing(new Person(
                null,
                "Alex",
                "Ionescu",
                "alex.ionescu@upb.ro",
                "1241EB",
                List.of("Java", "React", "Git")
        ));

        saveIfMissing(new Person(
                null,
                "Maria",
                "Georgescu",
                "maria.georgescu@upb.ro",
                "1241EC",
                List.of("PostgreSQL", "SQL", "Database Design")
        ));
    }

    private void saveIfMissing(Person person) {
        if (!personRepository.existsByEmail(person.getEmail())) {
            personRepository.save(person);
        }
    }
}
