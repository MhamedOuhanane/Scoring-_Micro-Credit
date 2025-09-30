package main.repository.interfaces;

import main.model.Person;

import java.util.Optional;

public interface PersonRepository {
    Optional<Person> inserPerson(Person person);
    Optional<Person> findPerson(Integer id);
    Boolean deletePerson(Person person);
}
