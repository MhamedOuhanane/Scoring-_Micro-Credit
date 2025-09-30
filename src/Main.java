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
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EmployeRepository employeRepository = new EmployeRepositoryImp();
        Employe employe = new Employe(
                "Dupont",
                "Jean",
                "jean.duponttu@example.com",
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
//            employe = employeRepository.findEmploye(7).orElseThrow(RuntimeException::new);
            List<Employe> employes = employeRepository.selectEmployes();
            employes.forEach(employe1 ->
                System.out.println("Insertion valid, id " + employe1.getId() + " " + employe1.getSalaire() + " | email:" +  employe1.getEmail())
            );
            System.out.println(employes);
        } catch (RuntimeException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}