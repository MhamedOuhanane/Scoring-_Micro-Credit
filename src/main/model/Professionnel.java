package main.model;

import main.enums.EnumRole;
import main.enums.EnumSitFam;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Professionnel extends Person{
    private Double revenu;
    private Double immatriculationFiscale;
    private String secteurActivite;
    private String Activite;




    public Professionnel(Integer id, String nom, String prenom, String email, LocalDate dateNaissance, String ville,
                         Integer nombreEnfants, Boolean investissement, Boolean placement, EnumSitFam situationFamiliale, LocalDateTime createdAt,
                         Integer score, EnumRole role, Double revenu, Double immatriculationFiscale, String secteurActivite, String Activite
    ) {
        super(id, nom, prenom, email, dateNaissance, ville, nombreEnfants, investissement, placement, situationFamiliale, createdAt, score, role);
        this.revenu = revenu;
        this.immatriculationFiscale = immatriculationFiscale;
        this.secteurActivite = secteurActivite;
        this.Activite = Activite;
    }

    public Professionnel(String nom, String prenom, String email, LocalDate dateNaissance, String ville,
                         Integer nombreEnfants, Boolean investissement, Boolean placement, EnumSitFam situationFamiliale, LocalDateTime createdAt,
                         Integer score, EnumRole role,Double revenu, Double immatriculationFiscale, String secteurActivite, String Activite
    ) {
        super(nom, prenom, email, dateNaissance, ville, nombreEnfants, investissement, placement, situationFamiliale, createdAt, score, role);
        this.revenu = revenu;
        this.immatriculationFiscale = immatriculationFiscale;
        this.secteurActivite = secteurActivite;
        this.Activite = Activite;
    }

    public Professionnel(Integer id, String nom, String prenom, String email, LocalDate dateNaissance, String ville, Integer nombreEnfants, Boolean investissement, Boolean placement, EnumSitFam situationFamiliale, LocalDateTime createdAt, Integer score, EnumRole role) {
        super(id, nom, prenom, email, dateNaissance, ville, nombreEnfants, investissement, placement, situationFamiliale, createdAt, score, role);
    }

    public Double getRevenu() {
        return revenu;
    }

    public void setRevenu(Double revenu) {
        this.revenu = revenu;
    }

    public Double getImmatriculationFiscale() {
        return immatriculationFiscale;
    }

    public void setImmatriculationFiscale(Double immatriculationFiscale) {
        this.immatriculationFiscale = immatriculationFiscale;
    }

    public String getSecteurActivite() {
        return secteurActivite;
    }

    public void setSecteurActivite(String secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    public String getActivite() {
        return Activite;
    }

    public void setActivite(String activite) {
        Activite = activite;
    }
}
