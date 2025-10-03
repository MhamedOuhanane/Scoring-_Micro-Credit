package main.view;

import main.controller.EmployeController;
import main.controller.ProfessionnelController;
import main.enums.EnumSecteur;
import main.service.interfaces.ProfessionnelService;
import main.utils.ValidationScanner;

import java.util.Map;

public class ProfessionnelView {
    private final ProfessionnelController professionnelController;

    public ProfessionnelView(ProfessionnelController professionnelController) {
        this.professionnelController = professionnelController;
    }

    public void createView(Map<String , Object> data) {
        int score = (int) data.get("score");
        System.out.print("🔹Saisir le revenu de Client (DH/mois): ");
        double revenu = ValidationScanner.getMontantInput();
        data.put("revenu", revenu);
        score = revenu < 3_000 ? score + 10 : revenu < 5_000 ? score + 15 : revenu < 8_000 ? score + 20 : revenu <10_000 ? score + 25 : score + 30;
        System.out.print("🔹Saisir l'anciennete de Client: ");
        int anciennete = ValidationScanner.getIntegerInput();
        score = anciennete < 1 ? score : anciennete < 2 ? score + 1 : anciennete < 5 ? score + 3 : score + 5;
        System.out.print("🔹Saisir le montant immatriculation Fiscale de Client: ");
        Double immatriculationFiscale = ValidationScanner.getMontantInput();
        data.put("immatriculationFiscale", immatriculationFiscale);
        System.out.println("🔹Sélectionnez le type de contrat : ");
        boolean sectAct = true;
        while (sectAct) {
            System.out.println("1. 🏗️ Construction");
            System.out.println("2. 📈 Commerce");
            System.out.println("3. 🛠️ Service");
            System.out.println("4. 👨‍🌾 Agriculture");
            System.out.print("Choix : ");
            int choix = ValidationScanner.getIntegerInput();
            switch (choix) {
                case 1:
                    data.put("secteurActivite", "Commerce");
                    sectAct = false;
                    break;
                case 2:
                    data.put("secteurActivite", "Service");
                    sectAct = false;
                    break;
                case 3:
                    data.put("secteurActivite", "Construction");
                    sectAct = false;
                    break;
                case 4:
                    data.put("secteurActivite", "Agriculture");
                    sectAct = false;
                    break;
                default:
                    System.out.println("Choix Invalide!");
            }
        }
        System.out.print("🔹Saisir l'Activite de Client: ");
        String Activite = ValidationScanner.getInfoStringInput("Activite");
        data.put("Activite", Activite);
        score += 30;
        score = score < 0 ? 0 : Math.min(score, 100);
        data.put("score", score);
        Map<String, Object> result = this.professionnelController.create(data);
        if (result.get("message") != "") {
            System.out.println(result.get("message"));
        } else if (result.get("erreur") != "") System.out.println(result.get("erreur"));

    }
}
