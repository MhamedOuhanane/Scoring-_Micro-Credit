package main.enums;

public enum EnumDecision {
    ACCORDIMMEDIAT("Accord instantané"),
    ETUDEMANUELLE("Vérification manuelle"),
    REFUS_AUTOMATIQUE("Rejet automatique");

    private final String description;


    EnumDecision(String description) {
        this.description = description;
    }

    public String getDescription() { return description; }
}
