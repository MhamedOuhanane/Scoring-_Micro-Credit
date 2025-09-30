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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
//            employe = employeRepository.inserEmploye(employe).orElseThrow(() -> new RuntimeException("Le employe est vide"));
//            employe = employeRepository.findEmploye(7).orElseThrow(RuntimeException::new);
//            List<Employe> employes = employeRepository.selectEmployes();
//            employes.forEach(employe1 ->
//            );
            System.out.println("Insertion valid, id " + employe.getId() + " " + employe.getSalaire() + " | email:" +  employe.getEmail());
//            System.out.println(employes);
//            Map<String, Object> updates = new HashMap<>();
//            updates.put("salaire", 90000.);
//            updates.put("poste", "Doctor");
//            updates.put("secteur", EnumSecteur.GRANDE_ENTREPRISE.toString());
//            updates.put("placement", true);
//            updates.put("dateNaissance", LocalDate.of(2000, 4, 30));
//
//            Employe newEmp = employeRepository.updateEmploye(8, updates).get();
//            System.out.println("Insertion valid, id " + newEmp.getId() + " | " + newEmp.getSalaire() + " | " + newEmp.getPoste() + " | " + newEmp.getSecteur().getDescription() + " | " + newEmp.getPlacement() + " | " + newEmp.getDateNaissance() );
            Employe newEmpl = employeRepository.findEmploye(8).get();
            if (employeRepository.deleteEmploye(newEmpl)) System.out.println("Suppression valid");
            else System.out.println("Suppression inValid");
        } catch (RuntimeException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}