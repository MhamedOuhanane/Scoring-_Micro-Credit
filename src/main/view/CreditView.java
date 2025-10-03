package main.view;

import main.controller.CreditController;
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
            System.out.println("\n===== MENU Conseiller =====");
            System.out.println("1. Ajouter un Credit");
            System.out.println("2. Supprimer un Credit");
            System.out.println("3. Rechercher un Credit par son ID");
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

                    break;
                case 4:
                    break;
                case 5:

                    break;
                case 6:
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
}
