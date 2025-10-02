package main.enums;

public enum StatutPaiement {
    PENDING("Paiement en attente"),
    PAYEATEMPS("Délai de paiement"),
    ENRETARD("En retard"),
    PAYEENRETARD("Paiement tardif"),
    IMPAYENONREGLE("Impayé non réglementé"),
    IMPAYEREGLE("Impayé réglé");

    private final String description;

    StatutPaiement(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
