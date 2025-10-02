package main.view;

import main.controller.EmployeController;
import main.enums.EnumSecteur;
import main.model.Employe;
import main.utils.ValidationScanner;

import java.util.Map;

public class EmployeView {
    private final EmployeController employeController;

    public EmployeView(EmployeController employeController) {
        this.employeController = employeController;
    }

    public void createView(Map<String , Object> data) {
        int score = (int) data.get("score");
        System.out.print("🔹Saisir le salaire de Client (DH/mois): ");
        double salaire = ValidationScanner.getMontantInput();
        data.put("salaire", salaire);
        score = salaire < 3_000 ? score + 10 : salaire < 5_000 ? score + 15 : salaire < 8_000 ? score + 20 : salaire <10_000 ? score + 25 : score + 30;
        System.out.print("🔹Saisir l'anciennete de Client: ");
        int anciennete = ValidationScanner.getIntegerInput();
        data.put("anciennete", anciennete);
        score = anciennete < 1 ? score : anciennete < 2 ? score + 1 : anciennete < 5 ? score + 3 : score + 5;
        System.out.print("🔹Saisir le poste de Client: ");
        String poste = ValidationScanner.getInfoStringInput("poste");
        data.put("poste", poste);
        System.out.println("🔹Sélectionnez le type de contrat : ");
        boolean sitfal = true;
        while (sitfal) {
            System.out.println("1. 👔 CDI");
            System.out.println("2. 📄 CDD");
            System.out.print("Choix : ");
            int choix = ValidationScanner.getIntegerInput();
            switch (choix) {
                case 1:
                    data.put("typeContrat", "CDI");

                    System.out.println("🔹Sélectionnez le secteur de travail : ");
                    boolean secteurSaisi = true;
                    while (secteurSaisi) {
                        System.out.println("1. 🏛️ CDI secteur public");
                        System.out.println("2. 🏢 CDI secteur privé (grande entreprise)");
                        System.out.println("3. 🏬 CDI secteur privé (PME)");
                        System.out.print("Choix : ");
                        int choix1 = ValidationScanner.getIntegerInput();
                        switch (choix1) {
                            case 1:
                                data.put("secteur", EnumSecteur.PUBLIC);
                                score += 25;
                                secteurSaisi = false;
                                break;
                            case 2:
                                data.put("secteur", EnumSecteur.GRANDE_ENTREPRISE);
                                score += 15;
                                secteurSaisi = false;
                                break;

                            case 3:
                                data.put("secteur", EnumSecteur.PME);
                                score += 12;
                                secteurSaisi = false;
                                break;
                            default:
                                System.out.println("Choix Invalide!");
                        }
                    }
                    sitfal = false;
                    break;
                case 2:
                    data.put("typeContrat", "CDI");
                    data.put("secteur", EnumSecteur.CDD);
                    score += 10;
                    sitfal = false;
                    break;
                default:
                    System.out.println("Choix Invalide!");
            }
        }
        score = score < 0 ? 0 : Math.min(score, 100);
        data.put("score", score);
        Map<String, Object> result = this.employeController.create(data);
        if (result.get("message") != "") {
            System.out.println(result.get("message"));

        } else if (result.get("erreur") != "") System.out.println(result.get("message"));

    }
}
