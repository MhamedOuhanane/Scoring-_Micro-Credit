package main.view;

import main.controller.CreditController;
import main.enums.EnumDecision;
import main.utils.ValidationScanner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CreditView {
    private final CreditController creditController;

    public CreditView(CreditController creditController) {
        this.creditController = creditController;
    }

    public void menuCredit() {
        boolean connection = true;
        while (connection) {
            System.out.println("\n===== MENU Credits =====");
            System.out.println("1. Ajouter un Credit");
            System.out.println("2. Rechercher un Credit par son ID");
            System.out.println("3. Validation des Credits");
            System.out.println("4. Afficher les Echecances d'un Credit");
            System.out.println("5. Afficher les Incidents d'un Credit");
            System.out.println("6. Quitter");
            System.out.print("Choix : ");

            int choix = ValidationScanner.getIntegerInput();
            switch (choix) {
                case 1:
                    this.createView();
                    break;
                case 2:
                    break;
                case 3:
                    this.validCredit();
                    break;
                case 4:
                    break;
                case 5:
                    connection = false;
                    break;
                default:
                    System.out.println("Choix Invalide!");
            }
        }
    }

    private void createView() {
        Map<String , Object> dataCredit = new HashMap<>();
        System.out.print("🔹Saisir l'id de Client: ");
        Integer person_id = ValidationScanner.getIntegerInput();
        dataCredit.put("person_id", person_id);
        System.out.print("🔹Saisissez le montant de la demande de crédit: ");
        Double montantDemande = ValidationScanner.getMontantInput();
        dataCredit.put("montantDemande", montantDemande);
        dataCredit.put("montantOctroye", montantDemande);
        System.out.print("🔹Saisir le type de Credit: ");
        String typeCredit = ValidationScanner.getInfoStringInput("type de Credit");
        dataCredit.put("typeCredit", typeCredit);
        dataCredit.put("dateCredit", LocalDateTime.now());

        Map<String, Object> result = this.creditController.create(dataCredit);
        if (!result.get("message").equals("")) {
            System.out.println(result.get("message"));
        } else if (!result.get("erreur").equals("")) System.out.println(result.get("erreur"));
    }

    private void validCredit() {
        Map<String , Object> updates = new HashMap<>();
        System.out.println("\n+--+--+ Validation d'un Client +--+--+");
        Map<String , Object> result = this.creditController.getAllETUDEMANUELLE();
        if (result.get("message") != "") {
            System.out.println(result.get("message"));
            System.out.print("🔹Saisir l'id de Credit: ");
            int id = ValidationScanner.getIntegerInput();
            boolean connect = true;
            while (connect) {
                System.out.println("\n===== Validation =====");
                System.out.println("🔹Voulez-vous valider ce crédit ? ");
                System.out.println("1. ✅ Oui");
                System.out.println("2. ❌ Non");
                System.out.println("3. 🔙 Quitter");
                System.out.print("Choix : ");

                int choix = ValidationScanner.getIntegerInput();
                switch (choix) {
                    case 1:
                        updates.put("decision", EnumDecision.ACCORDIMMEDIAT);
                        connect = false;
                        break;
                    case 2:
                        updates.put("decision", EnumDecision.REFUS_AUTOMATIQUE);
                        connect = false;
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Choix Invalide!");
                }
            }
            Map<String , Object> result1 = this.creditController.update(id, updates);
            if (!result1.get("message").equals("")) {
                System.out.println(result1.get("message"));
            } else if (!result1.get("erreur").equals("")) System.out.println(result1.get("erreur"));
        } else if (!result.get("erreur").equals("")) System.out.println(result.get("erreur"));
    }
}
