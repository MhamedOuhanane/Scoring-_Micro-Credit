package main.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidationScanner {
    private static final Scanner scanner = new Scanner(System.in);

    public static double getMontantInput() {
        Double input = null;
        while (input == null) {
            try {
                input = scanner.nextDouble();
                scanner.nextLine();
                if (input < 0)
                    throw new InputMismatchException("Le montant ne peut pas être saisi sous forme de valeur négative.");
            } catch (InputMismatchException exc) {
                System.out.println("🚫 Erreur : veuillez entrer un nombre entier positif.");
                scanner.nextLine();
                input = null;
            }
            if (input == null) System.out.print("Ré-entrez le montant: ");
        }

        return input;
    }

    public static int getIntegerInput() {
        Integer input = null;
        while (input == null) {
            try {
                input = scanner.nextInt();
                scanner.nextLine();
                if (input < 0) throw new InputMismatchException("Le choix est negative.");
            } catch (InputMismatchException exc) {
                System.out.println("🚫 Erreur : veuillez entrer un nombre entier positif.");
                scanner.nextLine();
                input = null;
            }
            if (input == null) System.out.print("Ré-entrez votre choix: ");
        }
        return input;
    }

    public static String getInfoStringInput(String type) {
        String input = null;
        while (input == null) {
            try {
                input = scanner.nextLine();
                String regexName = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s'-]{2,50}$";
                String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
                boolean pattern = Pattern.matches(Objects.equals(type, "email") ? regexEmail : regexName, input);
                if (!pattern) throw new InputMismatchException("⚠️ Le " + type + " saisi est invalide. ");
            } catch (InputMismatchException exc) {
                System.out.println("🚫 Le " + type + " saisi est invalide.");
                input = null;
            }
            if (input == null) System.out.print("Ré-entrez votre choix: ");
        }
        return input;
    }

    public static String getParagraphInput() {
        String input = null;
        while (input == null) {
            try {
                input = scanner.nextLine();
            } catch (InputMismatchException exc) {
                System.out.println("🚫 Le paragraphe saisi est invalide.");
            }
            if (input == null) System.out.print("Ré-entrez votre paragraphe: ");
        }
        return input;
    }

    public static LocalDate getDateInput() {
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (date == null) {
            String input = scanner.nextLine();
            try {
                date = LocalDate.parse(input, formatter);
                if (!date.isAfter(LocalDate.now())) {
                    System.out.println("🚫 La date doit être postérieure à aujourd'hui.");
                    date = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("⚠️ Format invalide, utilisez yyyy-MM-dd.");
                date = null;
            }
            if (date == null) System.out.print("Ré-entrez votre choix: ");
        }
        return date;
    }

    public static LocalDate getDateNaiInput() {
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (date == null) {
            String input = scanner.nextLine();
            try {
                date = LocalDate.parse(input, formatter);
                if (!date.isBefore(LocalDate.now().minusYears(18))) {
                    System.out.println("🚫 L'age doit être supérieure à 17 annee.");
                    date = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("⚠️ Format invalide, utilisez yyyy-MM-dd.");
                date = null;
            }
            if (date == null) System.out.print("Ré-entrez votre choix: ");
        }
        return date;
    }

    public static LocalDateTime getDateTimeInput() {
        LocalDateTime date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        while (date == null) {
            String input = scanner.nextLine();
            try {
                date = LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("⚠️ Format invalide, utilisez 'yyyy-MM-dd HH:mm'.");
            }
            if (date == null) System.out.print("Ré-entrez votre choix: ");
        }
        return date;
    }

    public static Boolean getBooleanInput() {
        String input = null;
        while (input == null) {
            try {
                input = scanner.nextLine();
                boolean pattern = Pattern.matches("^[yn]$", input);
                if (!pattern) throw new InputMismatchException("⚠️ Le chois saisi est invalide ('y' ou 'n'). ");
            } catch (InputMismatchException exc) {
                System.out.println("🚫 Le chois saisi est invalide ('y' ou 'n').");
                input = null;
            }
            if (input == null) System.out.print("Ré-entrez votre choix: ");
        }
        return input.equals("y");
    }
}
