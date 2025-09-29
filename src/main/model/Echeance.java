package main.model;

import main.enums.StatutPaiement;

import java.time.LocalDateTime;

public class Echeance {
    private Integer id;
    private LocalDateTime dateEcheance;
    private Double mensualite;
    private LocalDateTime datePaiement;
    private StatutPaiement statutPaiement;
    private Integer credit_id;

    public Echeance(Integer id, LocalDateTime dateEcheance, Double mensualite, LocalDateTime datePaiement, StatutPaiement statutPaiement, Integer credit_id) {
        this.id = id;
        this.dateEcheance = dateEcheance;
        this.mensualite = mensualite;
        this.datePaiement = datePaiement;
        this.statutPaiement = statutPaiement;
        this.credit_id = credit_id;
    }

    public Echeance(LocalDateTime dateEcheance, Double mensualite, LocalDateTime datePaiement, StatutPaiement statutPaiement, Integer credit_id) {
        this.dateEcheance = dateEcheance;
        this.mensualite = mensualite;
        this.datePaiement = datePaiement;
        this.statutPaiement = statutPaiement;
        this.credit_id = credit_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDateTime dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public Double getMensualite() {
        return mensualite;
    }

    public void setMensualite(Double mensualite) {
        this.mensualite = mensualite;
    }

    public LocalDateTime getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDateTime datePaiement) {
        this.datePaiement = datePaiement;
    }

    public StatutPaiement getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement(StatutPaiement statutPaiement) {
        this.statutPaiement = statutPaiement;
    }

    public Integer getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(Integer credit_id) {
        this.credit_id = credit_id;
    }
}
