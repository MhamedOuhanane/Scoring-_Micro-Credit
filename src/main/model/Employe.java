package main.model;

import main.enums.EnumSecteur;
import main.enums.EnumSitFam;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Employe extends Person{
    private Double salaire;
    private Integer anciennete;
    private String poste;
    private String typeContrat;
    private EnumSecteur secteur;

    public Employe(Integer id, String nom, String prenom, String email, LocalDate dateNaissance, String ville, Integer nombreEnfants,
                   Boolean investissement, Boolean placement, EnumSitFam situationFamiliale, LocalDateTime createdAt, Integer score,
                   Double salaire, Integer anciennete, String poste, String typeContrat, EnumSecteur secteur
    ) {
        super(id, nom, prenom, email, dateNaissance, ville, nombreEnfants, investissement, placement, situationFamiliale, createdAt, score);
        this.salaire = salaire;
        this.anciennete = anciennete;
        this.poste = poste;
        this.typeContrat = typeContrat;
        this.secteur = secteur;
    }

    public Employe(String nom, String prenom, String email, LocalDate dateNaissance, String ville, Integer nombreEnfants,
                   Boolean investissement, Boolean placement, EnumSitFam situationFamiliale, LocalDateTime createdAt, Integer score,
                   Double salaire, Integer anciennete, String poste, String typeContrat, EnumSecteur secteur
    ) {
        super(nom, prenom, email, dateNaissance, ville, nombreEnfants, investissement, placement, situationFamiliale, createdAt, score);
        this.salaire = salaire;
        this.anciennete = anciennete;
        this.poste = poste;
        this.typeContrat = typeContrat;
        this.secteur = secteur;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public Integer getAnciennete() {
        return anciennete;
    }

    public void setAnciennete(Integer anciennete) {
        this.anciennete = anciennete;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public EnumSecteur getSecteur() {
        return secteur;
    }

    public void setSecteur(EnumSecteur secteur) {
        this.secteur = secteur;
    }
}
