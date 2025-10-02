import main.enums.EnumDecision;
import main.enums.StatutPaiement;
import main.model.*;
import main.repository.impl.*;
import main.repository.interfaces.*;
import main.service.impl.CreditServiceImpl;
import main.service.impl.EcheanceServiceImpl;
import main.service.impl.EmployeServiceImpl;
import main.service.impl.ProfessionnelServiceImpl;
import main.service.interfaces.CreditService;
import main.service.interfaces.EcheanceService;
import main.service.interfaces.EmployeService;
import main.service.interfaces.ProfessionnelService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PersonRepository personRepository = new PersonRepositoryImpl();
        EmployeRepository employeRepository = new EmployeRepositoryImp();
        ProfessionnelRepository professionnelRepository = new ProfessionnelRepositoryImpl();
        CreditRepository creditRepository = new CreditRepositoryImpl();
        EcheanceRepository echeanceRepository = new EcheanceRepositoryImpl();

        EmployeService employeService = new EmployeServiceImpl(employeRepository);
        ProfessionnelService professionnelService = new ProfessionnelServiceImpl(professionnelRepository);
        CreditService creditService = new CreditServiceImpl(creditRepository, personRepository, employeService, professionnelService, echeanceRepository);

        Credit credit1 = new Credit(LocalDateTime.now(), 15_000., 15_000., 0.1, 6, "developpeur", EnumDecision.ETUDEMANUELLE, 9);
        try {
            credit1 = creditService.ajouterCredit(credit1);
            credit1 = creditService.findCredit(3);
            System.out.println("Trouver credit: " + credit1.getId() + "|" + credit1.getDateCredit());
            System.out.println("yu1");
            creditService.getAllCredits().forEach(credit -> System.out.println("Trouver credit: " + credit.getId() + "|" + credit.getDateCredit()));
            System.out.println("yu2");
            creditService.getPersonCredits(9).forEach(credit -> System.out.println("Trouver credit: " + credit.getId() + "|" + credit.getDateCredit()));
            System.out.println("yu3");
//            if (creditService.deleteCredit(credit1.getId())) System.out.println("delete success");
//            else System.out.println("delete invalid");

            System.out.println("Credit ajouter avec success");
        } catch (RuntimeException e) {
            System.out.println("Erreur : " + e);
        }
    }
}