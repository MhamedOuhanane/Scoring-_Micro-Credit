package main.repository.interfaces;

import main.model.Person;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PersonRepository {
    Optional<Person> insertPerson(Person person);
    Optional<Person> findPerson(Integer id);
    List<Person> selectPersons();
    Optional<Person> updatePerson(Person pers, Map<String, Object> updates);
    Boolean deletePerson(Person person);
}
