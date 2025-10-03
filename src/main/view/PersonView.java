package main.view;

import main.controller.ProfessionnelController;
import main.enums.EnumRole;
import main.enums.EnumSitFam;
import main.model.Person;
import main.utils.ValidationScanner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class PersonView {
    private final EmployeView employeView;
    private final ProfessionnelView professionnelView;

    public PersonView(EmployeView employeView, ProfessionnelView professionnelView) {
        this.employeView = employeView;
        this.professionnelView = professionnelView;
    }

    public void menuPerson() {
        boolean connection = true;
        while (connection) {
            System.out.println("\n===== MENU Conseiller =====");
            System.out.println("1. Ajouter un Client");
            System.out.println("2. Supprimer un Client");
            System.out.println("3. Rechercher un Client par son ID");
            System.out.println("4. Afficher les Credit d'un Client");
            System.out.println("5. Afficher les Echeance d'un Client");
            System.out.println("6. Afficher les Incidents d'un Client");
            System.out.println("7. Quitter");
            System.out.print("Choix : ");

            int choix = ValidationScanner.getIntegerInput();
            switch (choix) {
                case 1:
                    this.createView();
                    break;
                case 2:
                    boolean conRole = true;
                    while (conRole) {
                        System.out.println("\n🔹 Choisissez le rôle Role: ");
                        System.out.println("1. 👨‍💼 Employé");
                        System.out.println("2. 💼 Professionnel");
                        System.out.println("6. 🔙 Quitter");
                        System.out.print("Choix : ");

                        int choix1 = ValidationScanner.getIntegerInput();
                        switch (choix1) {
                            case 1:
                                this.employeView.deleteView();
                                break;
                            case 2:
                                this.professionnelView.deleteView();
                                break;
                            case 3:
                                conRole = false;
                                break;
                            default:
                                System.out.println("Choix Invalide!");
                        }
                    }
                    break;
                case 3:
                    boolean conRole1 = true;
                    while (conRole1) {
                        System.out.println("\n🔹 Choisissez le rôle Role: ");
                        System.out.println("1. 👨‍💼 Employé");
                        System.out.println("2. 💼 Professionnel");
                        System.out.println("6. 🔙 Quitter");
                        System.out.print("Choix : ");

                        int choix1 = ValidationScanner.getIntegerInput();
                        switch (choix1) {
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                conRole1 = false;
                                break;
                            default:
                                System.out.println("Choix Invalide!");
                        }
                    }
                    break;
                case 4:
                    boolean conRole2 = true;
                    while (conRole2) {
                        System.out.println("\n🔹 Choisissez le rôle Role: ");
                        System.out.println("1. 👨‍💼 Employé");
                        System.out.println("2. 💼 Professionnel");
                        System.out.println("6. 🔙 Quitter");
                        System.out.print("Choix : ");

                        int choix1 = ValidationScanner.getIntegerInput();
                        switch (choix1) {
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                conRole2 = false;
                                break;
                            default:
                                System.out.println("Choix Invalide!");
                        }
                    }                    break;
                case 5:
                    boolean conRole3 = true;
                    while (conRole3) {
                        System.out.println("\n🔹 Choisissez le rôle Role: ");
                        System.out.println("1. 👨‍💼 Employé");
                        System.out.println("2. 💼 Professionnel");
                        System.out.println("6. 🔙 Quitter");
                        System.out.print("Choix : ");

                        int choix1 = ValidationScanner.getIntegerInput();
                        switch (choix1) {
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                conRole3 = false;
                                break;
                            default:
                                System.out.println("Choix Invalide!");
                        }
                    }
                    break;
                case 6:
                    boolean conRole4 = true;
                    while (conRole4) {
                        System.out.println("\n🔹 Choisissez le rôle Role: ");
                        System.out.println("1. 👨‍💼 Employé");
                        System.out.println("2. 💼 Professionnel");
                        System.out.println("6. 🔙 Quitter");
                        System.out.print("Choix : ");

                        int choix1 = ValidationScanner.getIntegerInput();
                        switch (choix1) {
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                conRole4 = false;
                                break;
                            default:
                                System.out.println("Choix Invalide!");
                        }
                    }
                    break;
                case 7:
                    connection = false;
                    break;
                default:
                    System.out.println("Choix Invalide!");
            }
        }
    }

    private void createView() {
        Map<String, Object> dataPerson = new HashMap<>();
        int score = 0;
        System.out.println("\n+--+--+ Ajouter un Client +--+--+");
        boolean conRole = true;
        while (conRole) {
            System.out.println("\n🔹 Choisissez le rôle Role: ");
            System.out.println("1. 👨‍💼 Employé");
            System.out.println("2. 💼 Professionnel");
            System.out.print("Choix : ");

            int choix = ValidationScanner.getIntegerInput();
            switch (choix) {
                case 1:
                    dataPerson.put("role", EnumRole.EMPLOYE);
                    conRole = false;
                    break;
                case 2:
                    dataPerson.put("role", EnumRole.PEOFESSIONNEL);
                    conRole = false;
                    break;
                default:
                    System.out.println("Choix Invalide!");
            }
        }
        System.out.print("🔹Saisir le nom de Client: ");
        String nom = ValidationScanner.getInfoStringInput("nom");
        dataPerson.put("nom", nom);
        System.out.print("🔹Saisir le prenom de Client: ");
        String prenom = ValidationScanner.getInfoStringInput("prenom");
        dataPerson.put("prenom", prenom);
        System.out.print("🔹Saisir l'email de Client: ");
        String email = ValidationScanner.getInfoStringInput("email");
        dataPerson.put("email", email);
        System.out.print("🔹Saisir la date de naissance de Client (format 'yyyy-MM-dd') (>17): ");
        LocalDate dateNaissance = ValidationScanner.getDateNaiInput();
        dataPerson.put("dateNaissance", dateNaissance);
        int age = Person.calculAgePerson(dateNaissance);
        score = (age > 17 && age <= 25) ? score + 4 : (age > 25 && age <= 35) ? score + 8 : (age > 35 && age <= 55) ? score + 10 : score + 6;
        System.out.print("🔹Saisir la ville de Client: ");
        String ville = ValidationScanner.getInfoStringInput("ville");
        dataPerson.put("ville", ville);
        System.out.print("🔹Saisir le nombre des enfants de Client: ");
        int nombreEnfants = ValidationScanner.getIntegerInput();
        dataPerson.put("nombreEnfants", nombreEnfants);
        score = nombreEnfants == 0 ? score + 2 : (nombreEnfants > 0 && nombreEnfants <= 2) ? score + 1 : score;
        System.out.print("🔹Le client a-t-il un investissement ?(y/n): ");
        Boolean investissement = ValidationScanner.getBooleanInput();
        dataPerson.put("investissement", investissement);
        score = investissement ? score + 10 : score;
        System.out.print("🔹Le client a-t-il un placement ?(y/n): ");
        Boolean placement = ValidationScanner.getBooleanInput();
        dataPerson.put("placement", placement);
        score = placement ? score + 10 : score;
        System.out.println("🔹Sélectionnez la Situation familiale : ");
        boolean sitfal = true;
        while (sitfal) {
            System.out.println("1. 💐 Marié");
            System.out.println("2. 🙅‍♂️ Célibataire");
            System.out.print("Choix : ");
            int choix = ValidationScanner.getIntegerInput();
            switch (choix) {
                case 1:
                    dataPerson.put("situationFamiliale", EnumSitFam.MARIE);
                    score += 3;
                    sitfal = false;
                    break;
                case 2:
                    dataPerson.put("situationFamiliale", EnumSitFam.CELIBATAIRE);
                    score += 2;
                    sitfal = false;
                    break;
                default:
                    System.out.println("Choix Invalide!");
            }
        }
        dataPerson.put("createdAt", LocalDateTime.now());
        dataPerson.put("score", score);
        if (dataPerson.get("role") == EnumRole.EMPLOYE) employeView.createView(dataPerson);
        else this.professionnelView.createView(dataPerson);
    }

//    private void payeEch
}
