import main.config.DatabaseConfig;
import main.enums.EnumSecteur;
import main.enums.EnumSitFam;
import main.model.Employe;
import main.model.Person;
import main.model.Professionnel;
import main.repository.impl.EmployeRepositoryImp;
import main.repository.impl.ProfessionnelRepositoryImpl;
import main.repository.interfaces.EmployeRepository;
import main.repository.interfaces.ProfessionnelRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ProfessionnelRepository professionnelRepository = new ProfessionnelRepositoryImpl();
        Professionnel professionnel = new Professionnel(
                "Dupont",
                "Jean",
                "jean.dupodnttuws@example.com",
                LocalDate.of(1990, 5, 15),
                "Paris",
                2,
                true,
                false,
                EnumSitFam.MARIE,
                LocalDateTime.now(),
                85,
                6000.,
                400.,
                "Informateur",
                "CDI"
        );

        try {
            professionnel = professionnelRepository.inserProfessionnel(professionnel).orElseThrow(() -> new RuntimeException("Le professionnel est vide"));
//            System.out.println("Le compte est ajouter avec success id : " + professionnel.getId() + " | Email: " + professionnel.getEmail());
//            professionnel = professionnelRepository.findProfessionnel(1).orElseThrow((RuntimeException::new));
            List<Professionnel> professionnels = professionnelRepository.selectProfessionnels();
            professionnels.forEach(professionnel1 -> System.out.println("id : " + professionnel1.getId() + " | " + professionnel1.getEmail()));
        } catch (RuntimeException e) {
            System.out.println("Erreur : " + e.getCause().getMessage());
        }
    }
}