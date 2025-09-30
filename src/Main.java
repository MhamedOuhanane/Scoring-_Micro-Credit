import main.config.DatabaseConfig;
import main.enums.EnumDecision;
import main.enums.EnumSecteur;
import main.enums.EnumSitFam;
import main.model.Credit;
import main.model.Employe;
import main.model.Person;
import main.model.Professionnel;
import main.repository.impl.CreditRepositoryImpl;
import main.repository.impl.EmployeRepositoryImp;
import main.repository.impl.ProfessionnelRepositoryImpl;
import main.repository.interfaces.CreditRepository;
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
        CreditRepository creditRepository = new CreditRepositoryImpl();
        Credit credit = new Credit(LocalDateTime.now(), 15000., 12000., 0.05, 6, "Arich", EnumDecision.ACCORDIMMEDIAT, 3);

        try {
//            Credit credit1 = creditRepository.inserCredit(credit).orElseThrow(RuntimeException::new);
//            System.out.println("Insertion valid. id: " + credit1.getId() + " | date: " + credit1.getDateCredit() + " | decision: " + credit1.getDecision().getDescription());
//            Credit credit2 = creditRepository.findCredit(2).get();
//            System.out.println("InseSelection valid. id: " + credit2.getId() + " | date: " + credit2.getDateCredit() + " | decision: " + credit2.getDecision().getDescription());
            List<Credit> credits = creditRepository.selectPersonCredits(2);
            credits.forEach(credit1 -> System.out.println("InseSelection valid. id: " + credit1.getId() + " | date: " + credit1.getDateCredit() + " | decision: " + credit1.getDecision().getDescription()));

//            Map<String , Object> updates = new HashMap<>();
//            updates.put("montantDemande", 17000);
//            updates.put("tauxInteret", 0.1);
//            updates.put("dureeenMois", 7);
//            updates.put("decision", "REFUS_AUTOMATIQUE");
//
//            Credit credit2 = creditRepository.updateCredit(1, updates).get();
//            System.out.println("InseSelection valid. id: " + credit2.getId() + " | date: " + credit2.getDateCredit() + " | decision: " + credit2.getDecision().getDescription());
//            if (creditRepository.deleteCredit(credit2)) System.out.println("Suppression valid");
//            else System.out.println("Suppression invalid");

        } catch (RuntimeException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}