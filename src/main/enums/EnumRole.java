package main.enums;

public enum EnumRole {
    EMPLOYE("employé"),
    PEOFESSIONNEL("professionnel");

    private final String description;


    EnumRole(String description) {
        this.description = description;
    }

    public String getDescription() { return description; }
}
