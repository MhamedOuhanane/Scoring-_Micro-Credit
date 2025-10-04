package main.view;

import main.controller.CreditController;
import main.controller.EcheanceController;
import main.enums.EnumDecision;
import main.enums.StatutPaiement;
import main.model.Echeance;
import main.utils.ValidationScanner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CreditView {
    private final CreditController creditController;
    private final EcheanceController echeanceController;

    public CreditView(CreditController creditController, EcheanceController echeanceController) {
        this.creditController = creditController;
        this.echeanceController = echeanceController;
    }

    public void menuCredit() {
        boolean connection = true;
        while (connection) {
            System.out.println("\n===== MENU Credits =====");
            System.out.println("1. Ajouter un Credit");
            System.out.println("2. Rechercher un Credit par son ID");
            System.out.println("3. Validation des Credits");
            System.out.println("4. Afficher les Credit");
            System.out.println("5. Afficher les Echecances d'un Credit");
            System.out.println("6. Afficher les Incidents d'un Credit");
            System.out.println("7. Remboursement de crédit");
            System.out.println("8. Quitter");
            System.out.print("Choix : ");

            int choix = ValidationScanner.getIntegerInput();
            switch (choix) {
                case 1:
                    System.out.println("\n+--+--+ Ajouter un Credit +--+--+");
                    this.createView();
                    break;
                case 2:
                    this.findView();
                    System.out.println("\n+--+--+ Trouver un Credit par son Id +--+--+");
                    break;
                case 3:
                    this.validCredit();
                    System.out.println("\n+--+--+ Validation d'un Client +--+--+");
                    break;
                case 4:
                    System.out.println("\n+--+--+ Affichage des Credits +--+--+");
                    this.affichageView();
                    break;
                case 5:
                    System.out.println("\n+--+--+ Afficher les Echecances d'un Credit +--+--+");
                    this.getEcheances();
                    break;
                case 6:
                    System.out.println("\n+--+--+ Afficher les Incidents d'un Credit +--+--+");
                    getIncidents();
                    break;
                case 7:
                    System.out.println("\n+--+--+ Remboursement de crédit +--+--+");
                    this.remboursementView();
                    break;
                case 8:
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

    private void findView() {
        System.out.print("🔹Saisir Id de Credit: ");
        Integer id = ValidationScanner.getIntegerInput();

        System.out.println(this.creditController.find(id));
    }

    private void affichageView() {
        System.out.println(this.creditController.getAllCredit());
    }

    private void getEcheances() {
        System.out.print("🔹Saisir Id de Credit: ");
        Integer id = ValidationScanner.getIntegerInput();
        System.out.println(this.creditController.getEcheances(id));

    }

    private void getIncidents() {
        System.out.print("🔹Saisir Id de Credit: ");
        Integer id = ValidationScanner.getIntegerInput();
        System.out.println(this.creditController.getIncedents(id));
    }

    private void remboursementView() {
        System.out.print("🔹Saisir Id de Credit: ");
        Integer id = ValidationScanner.getIntegerInput();
        System.out.println(this.creditController.getEcheances(id));
        Echeance echeance = this.creditController.getEcheanceRemb(id);
        if (echeance != null) {
            if (echeance.getDateEcheance().isBefore(LocalDateTime.now())) {
                System.out.print("🔹Saisissez le montant du paiement d'echeance d'ID (" + echeance.getId() + "): ");
                double mensualite = ValidationScanner.getMontantInput();
                boolean connec = true;
                while (connec) {
                    if (!echeance.getMensualite().equals(mensualite)) {
                        System.out.print("⚠️ Montant saisi incorrect. Veuillez ressaisir le montant exact de la mensualité: ");
                        mensualite = ValidationScanner.getMontantInput();
                    } else connec = false;
                }
                Map<String, Object> updates = new HashMap<>();
                updates.put("mensualite", mensualite);
                StatutPaiement statutPaiement = echeance.getStatutPaiement();
                if (statutPaiement.equals(StatutPaiement.PENDING)) statutPaiement = StatutPaiement.PAYEATEMPS;
                else if (statutPaiement.equals(StatutPaiement.ENRETARD)) statutPaiement = StatutPaiement.PAYEENRETARD;
                else if (statutPaiement.equals(StatutPaiement.IMPAYENONREGLE)) statutPaiement = StatutPaiement.IMPAYEREGLE;
                updates.put("statutPaiement", statutPaiement);
                updates.put("datePaiement", LocalDateTime.now());

                System.out.println(this.echeanceController.update(echeance.getId(), updates));
            } else System.out.println("⚠️ La date de paiement pour cette échéance n’est pas encore arrivée. Vous ne pouvez pas rembourser avant le " + echeance.getDateEcheance().toLocalDate() + ".");
        } else System.out.println("ℹ️ Ce crédit ne contient actuellement aucune échéance impayée à rembourser.");
    }
}
