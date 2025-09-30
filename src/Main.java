import main.config.DatabaseConfig;
import main.enums.EnumSitFam;
import main.model.Person;
import main.repository.impl.PersonRepositoryImpl;
import main.repository.interfaces.PersonRepository;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PersonRepository personRepository = new PersonRepositoryImpl();
        Person person = new Person(
                "Dupont",
                "Jean",
                "jean.dupont@example.com",
                LocalDate.of(1990, 5, 15),
                "Paris",
                2,
                true,
                false,
                EnumSitFam.MARIE,
                LocalDateTime.now(),
                85
        );

        try {
//            person = personRepository.inserPerson(person).orElseThrow(() -> new RuntimeException("Le person est vide"));
//            System.out.println("Insertion valid");
            Person newPerson = personRepository.findAccount(1).orElseThrow(() -> new RuntimeException("Le person est vide"));
            System.out.println("find person: " + newPerson.getNom() + newPerson.getEmail());
            Boolean deletePerson = personRepository.deleteAccount(newPerson);
            if (deletePerson) {
                System.out.println("suppression valide");
            } else {

                System.out.println("suppression invalide!");
            }
        } catch (RuntimeException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}