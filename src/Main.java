import main.config.DatabaseConfig;
import main.enums.EnumDecision;
import main.enums.EnumSecteur;
import main.enums.EnumSitFam;
import main.enums.StatutPaiement;
import main.model.*;
import main.repository.impl.CreditRepositoryImpl;
import main.repository.impl.EcheanceRepositoryImpl;
import main.repository.impl.EmployeRepositoryImp;
import main.repository.impl.ProfessionnelRepositoryImpl;
import main.repository.interfaces.CreditRepository;
import main.repository.interfaces.EcheanceRepository;
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
        EcheanceRepository echeanceRepository = new EcheanceRepositoryImpl();
        Echeance echeance1 = new Echeance(LocalDateTime.now(), 3000., LocalDateTime.now().plusMinutes(1), StatutPaiement.IMPAYEREGLE, 1);
        Echeance echeance2 = new Echeance(LocalDateTime.now().plusMinutes(1), 2000., LocalDateTime.now().plusMinutes(2), StatutPaiement.IMPAYENONREGLE, 1);
        try {
            echeance1 = echeanceRepository.inserEcheance(echeance1).get();
            System.out.println("echeance1 inseret avec success");
            echeance2 = echeanceRepository.inserEcheance(echeance2).get();
            System.out.println("echeance2 inseret avec success");

            echeanceRepository.selectEcheances().forEach(ech -> System.out.println("Id "+ ech.getId() + " | mensualite: "+ ech.getMensualite()+" | status: " + ech.getStatutPaiement().getDescription()));
            Echeance echeance3 = echeanceRepository.findEcheance(2).get();
            System.out.println("echeance3 Id "+ echeance3.getId() + " | mensualite: "+ echeance3.getMensualite()+" | status: " + echeance3.getStatutPaiement().getDescription());

        } catch (RuntimeException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}