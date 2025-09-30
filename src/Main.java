import main.config.DatabaseConfig;
import main.enums.EnumSecteur;
import main.enums.EnumSitFam;
import main.model.Employe;
import main.model.Person;
import main.repository.impl.EmployeRepositoryImp;
import main.repository.interfaces.EmployeRepository;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EmployeRepository employeRepository = new EmployeRepositoryImp();
        Employe employe = new Employe(
                "Dupont",
                "Jean",
                "jean.dupontt@example.com",
                LocalDate.of(1990, 5, 15),
                "Paris",
                2,
                true,
                false,
                EnumSitFam.MARIE,
                LocalDateTime.now(),
                85,
                6000.,
                5,
                "Informateur",
                "CDI",
                EnumSecteur.PME
        );

        try {
            employe = employeRepository.inserEmploye(employe).orElseThrow(() -> new RuntimeException("Le employe est vide"));

            System.out.println("Insertion valid, id " + employe.getId() + " " + employe.getSalaire());
        } catch (RuntimeException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}