package main.model;

import main.enums.EnumDecision;

import java.time.LocalDateTime;

public class Credit {
    private Integer id;
    private LocalDateTime dateCredit;
    private Double montantDemande;
    private Double montantOctroye;
    private Double tauxInteret;
    private Integer dureeenMois;
    private String typeCredit;
    private EnumDecision decision;
    private Integer person_id;

    public Credit(Integer id, LocalDateTime dateCredit, Double montantDemande, Double montantOctroye, Double tauxInteret,
                  Integer dureeenMois, String typeCredit, EnumDecision decision, Integer person_id
    ) {
        this.id = id;
        this.dateCredit = dateCredit;
        this.montantDemande = montantDemande;
        this.montantOctroye = montantOctroye;
        this.tauxInteret = tauxInteret;
        this.dureeenMois = dureeenMois;
        this.typeCredit = typeCredit;
        this.decision = decision;
        this.person_id = person_id;
    }

    public Credit(LocalDateTime dateCredit, Double montantDemande, Double montantOctroye, Double tauxInteret,
                  Integer dureeenMois, String typeCredit, EnumDecision decision, Integer person_id
    ) {
        this.dateCredit = dateCredit;
        this.montantDemande = montantDemande;
        this.montantOctroye = montantOctroye;
        this.tauxInteret = tauxInteret;
        this.dureeenMois = dureeenMois;
        this.typeCredit = typeCredit;
        this.decision = decision;
        this.person_id = person_id;
    }

    public Credit(LocalDateTime dateCredit, Double montantDemande, Double montantOctroye, String typeCredit, Integer person_id
    ) {
        this.dateCredit = dateCredit;
        this.montantDemande = montantDemande;
        this.montantOctroye = montantOctroye;
        this.typeCredit = typeCredit;
        this.person_id = person_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateCredit() {
        return dateCredit;
    }

    public void setDateCredit(LocalDateTime dateCredit) {
        this.dateCredit = dateCredit;
    }

    public Double getMontantDemande() {
        return montantDemande;
    }

    public void setMontantDemande(Double montantDemande) {
        this.montantDemande = montantDemande;
    }

    public Double getMontantOctroye() {
        return montantOctroye;
    }

    public void setMontantOctroye(Double montantOctroye) {
        this.montantOctroye = montantOctroye;
    }

    public Double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(Double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public Integer getDureeenMois() {
        return dureeenMois;
    }

    public void setDureeenMois(Integer dureeenMois) {
        this.dureeenMois = dureeenMois;
    }

    public String getTypeCredit() {
        return typeCredit;
    }

    public void setTypeCredit(String typeCredit) {
        this.typeCredit = typeCredit;
    }

    public EnumDecision getDecision() {
        return decision;
    }

    public void setDecision(EnumDecision decision) {
        this.decision = decision;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public void generatedDureeMois(Double montant) {
        if (montant <= 24_000.) {
            this.dureeenMois = 6;
            this.tauxInteret = 0.1;
        } else if (montant <= 60_000.) {
            this.dureeenMois = 10;
            this.tauxInteret = 0.2;
        } else if (montant <= 150_000.) {
            this.dureeenMois = 15;
            this.tauxInteret = 0.3;
        } else {
            this.dureeenMois = 24;
            this.tauxInteret = 0.4;
        }
    }
}
