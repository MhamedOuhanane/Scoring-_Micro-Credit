import main.enums.StatutPaiement;
import main.model.*;
import main.repository.impl.EcheanceRepositoryImpl;
import main.repository.impl.IncidentRepositoryImpl;
import main.repository.interfaces.EcheanceRepository;
import main.repository.interfaces.IncidentRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        IncidentRepository incidentRepository = new IncidentRepositoryImpl();
        Incident incident1 = new Incident(LocalDateTime.now(), 90, StatutPaiement.ENRETARD, 4);
        Incident incident2 = new Incident(LocalDateTime.now(), 70, StatutPaiement.IMPAYENONREGLE, 5);
        try {
            System.out.println("|||| Insert ||||");
            incident1 = incidentRepository.insertIncident(incident1).get();
            System.out.println("incident1 inseret avec success");
            incident2 = incidentRepository.insertIncident(incident2).get();
            System.out.println("incident2 inseret avec success");

            incidentRepository.selectIncidents().forEach(ech -> System.out.println("Id "+ ech.getId() + " | Date : " + ech.getDateIncident().toString() + " | score: "+ ech.getScore()+" | status: " + ech.getTypeIncident().getDescription()));
            Incident incident3 = incidentRepository.findIncident(2).orElseThrow(RuntimeException::new);
            System.out.println("incident3 " + incident3.getId() + " | Date : " + incident3.getDateIncident().toString() + " | score: "+ incident3.getScore()+" | status: " + incident3.getTypeIncident().getDescription());
            incidentRepository.selectEcheanceIncidents(5).forEach(ech -> System.out.println("Id "+ ech.getId() + " | Date : " + ech.getDateIncident().toString() + " | score: "+ ech.getScore()+" | status: " + ech.getTypeIncident().getDescription()));
        } catch (RuntimeException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}