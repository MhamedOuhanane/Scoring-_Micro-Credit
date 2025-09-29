package main.model;

public enum EnumSitFam {
    CELIBATAIRE("Célibataire"),
    MARIE("Marié(e)"),
    VEUF("Veuf(ve)");

    private final String description;

    EnumSitFam(String description) { this.description = description; }

    public String getDescription() { return description; }
}
