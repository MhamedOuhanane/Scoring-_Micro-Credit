
import main.controller.CreditController;
import main.controller.EcheanceController;
import main.controller.EmployeController;
import main.controller.ProfessionnelController;
import main.model.Professionnel;
import main.repository.impl.*;
import main.repository.interfaces.*;
import main.service.impl.*;
import main.service.interfaces.*;
import main.utils.ValidationScanner;
import main.view.CreditView;
import main.view.EmployeView;
import main.view.PersonView;
import main.view.ProfessionnelView;

import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PersonRepository personRepository = new PersonRepositoryImpl();
        EmployeRepository employeRepository = new EmployeRepositoryImp();
        ProfessionnelRepository professionnelRepository = new ProfessionnelRepositoryImpl();
        CreditRepository creditRepository = new CreditRepositoryImpl();
        EcheanceRepository echeanceRepository = new EcheanceRepositoryImpl();
        IncidentRepository incidentRepository = new IncidentRepositoryImpl();

        PersonService personService = new PersonServiceImpl(personRepository);
        EmployeService employeService = new EmployeServiceImpl(employeRepository, personRepository);
        ProfessionnelService professionnelService = new ProfessionnelServiceImpl(professionnelRepository, personRepository);
        CreditService creditService = new CreditServiceImpl(creditRepository, personRepository, employeService, professionnelService, echeanceRepository);
        EcheanceService echeanceService = new EcheanceServiceImpl(echeanceRepository, creditService, personService, incidentRepository);
        IncidentService incidentService = new IncidentServiceImpl(incidentRepository, creditService, echeanceService, personRepository);

        EmployeController employeController = new EmployeController(employeService);
        ProfessionnelController professionnelController = new ProfessionnelController(professionnelService);
        CreditController creditController = new CreditController(creditService, incidentService);

        EmployeView employeView = new EmployeView(employeController);
        ProfessionnelView professionnelView = new ProfessionnelView(professionnelController);
        PersonView personView = new PersonView(employeView, professionnelView);
        CreditView creditView = new CreditView(creditController);
        EcheanceController echeanceController = new EcheanceController(echeanceService, personService);

        // Schedules
        echeanceController.scheduleTraitementDate();

        boolean connection = true;
        while (connection) {
            System.out.println("\n===== MENU Application =====");
            System.out.println("1. Gerer les Clients");
            System.out.println("2. Gerer les Credits");
            System.out.println("3. Gerer les Echeances");
            System.out.println("4. Gerer les Incidents");
            System.out.println("5. Quitter");
            System.out.print("Choix : ");

            int choix = ValidationScanner.getIntegerInput();

            switch (choix) {
                case 1:
                    personView.menuPerson();
                    break;
                case 2:
                    creditView.menuCredit();
                    break;
                case 3:
                    break;
                case 4:
                    break;

                case 5:
                    connection = false;

                    break;
                default:
                    System.out.println("Choix invalide!");

            }
        }
    }
}