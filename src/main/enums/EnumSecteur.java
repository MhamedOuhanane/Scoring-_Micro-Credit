package main.enums;

public enum EnumSecteur {
    PUBLIC("Secteur public"),
    GRANDE_ENTREPRISE("Grandes entreprises"),
    PME("Petites et moyennes entreprises");

    private final String description;

    EnumSecteur(String description) { this.description = description; }

    public String getDescription() { return description; }
}
