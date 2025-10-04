package main.service.impl;

import main.model.Employe;
import main.model.Person;
import main.repository.interfaces.PersonRepository;
import main.service.interfaces.PersonService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person findPerson(Integer id) {
        if (id == null) throw new  IllegalArgumentException("L'id person ne peut pas être null");
        try {
            return personRepository.findPerson(id)
                    .orElseThrow(() -> new RuntimeException("Aucun person trouvé avec l'id: " + id));
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public Person updatePerson(Integer id, Map<String, Object> update) {
        if (id == null) throw new  IllegalArgumentException("L'id person ne peut pas être null");
        if (update.isEmpty()) throw new  RuntimeException("Les modifications ne peut pas être vide");
        try {
            Person person = this.findPerson(id);
            return personRepository.updatePerson(person, update)
                    .orElseThrow(() -> new RuntimeException("Impossible de modifier person d'id: " + id));
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void traitmentAnciennete() {
        try {
            List<Person> personList = this.personRepository.selectPersons();
            LocalDate today = LocalDate.now();
            personList.forEach(person -> {
                int score = person.getScore();
                LocalDate createAt = person.getCreatedAt().toLocalDate();
                if (today.equals(createAt.plusYears(1))) person.setScore(score + 3);
                if (today.equals(createAt.plusYears(3))) person.setScore(score + 2);
            });

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
