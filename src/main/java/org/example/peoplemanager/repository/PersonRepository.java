package org.example.peoplemanager.repository;

import org.example.peoplemanager.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository
        extends JpaRepository<Person, Long> {
    boolean existsByEmail(String email);
}