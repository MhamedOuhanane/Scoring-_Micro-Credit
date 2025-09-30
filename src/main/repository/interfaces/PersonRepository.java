package main.repository.interfaces;

import main.model.Person;

public interface PersonRepository {
    Person inserPerson(Person person);
    Person findAccount(Integer id);
    Boolean deleteAccount(Person person);
}
