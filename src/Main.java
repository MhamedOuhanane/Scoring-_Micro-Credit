import main.enums.StatutPaiement;
import main.model.*;
import main.repository.impl.EcheanceRepositoryImpl;
import main.repository.interfaces.EcheanceRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EcheanceRepository echeanceRepository = new EcheanceRepositoryImpl();
        Echeance echeance1 = new Echeance(LocalDateTime.now(), 3000., LocalDateTime.now().plusMinutes(1), StatutPaiement.IMPAYEREGLE, 1);
        Echeance echeance2 = new Echeance(LocalDateTime.now().plusMinutes(1), 2000., LocalDateTime.now().plusMinutes(2), StatutPaiement.IMPAYENONREGLE, 1);
        try {
//            echeance1 = echeanceRepository.inserEcheance(echeance1).get();
//            System.out.println("echeance1 inseret avec success");
//            echeance2 = echeanceRepository.inserEcheance(echeance2).get();
//            System.out.println("echeance2 inseret avec success");
//
//            Map<String ,Object> updates = new HashMap<>();
//            updates.put( "dateEcheance", LocalDateTime.parse("2000-03-01T11:03:50"));
//            updates.put( "mensualite",9900. );
//            updates.put( "datePaiement",LocalDateTime.parse("2000-03-01T11:03:50") );
//            updates.put( "statutPaiement",StatutPaiement.ENRETARD.toString() );

//            echeance1 = echeanceRepository.updateEcheance(1, updates).get();
            echeanceRepository.selectCreditEcheances(1).forEach(ech -> System.out.println("Id "+ ech.getId() + " | mensualite: "+ ech.getMensualite()+" | status: " + ech.getStatutPaiement().getDescription()));
            Echeance echeance3 = echeanceRepository.findEcheance(92).orElseThrow(RuntimeException::new);
            System.out.println("echeance3 Id "+ echeance3.getId() + " | mensualite: "+ echeance3.getMensualite()+" | status: " + echeance3.getStatutPaiement().getDescription());
            if (echeanceRepository.deleteEcheance(echeance1)) System.out.println("Suppression avec success!!!");
//            else System.out.println("Suppression inValid!!!");
//            echeanceRepository.selectCreditEcheances(1).forEach(ech -> System.out.println("Id "+ ech.getId() + " | mensualite: "+ ech.getMensualite()+" | status: " + ech.getStatutPaiement().getDescription()));
        } catch (RuntimeException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}