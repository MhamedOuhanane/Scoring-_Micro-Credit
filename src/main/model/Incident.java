package main.model;

import main.enums.StatutPaiement;

import java.time.LocalDateTime;

public class Incident {
    private Integer id;
    private LocalDateTime dateIncident;
    private Integer score;
    private StatutPaiement typeIncident;
    private Integer echeance_id;

    public Incident(Integer id, LocalDateTime dateIncident, Integer score, StatutPaiement typeIncident, Integer echeance_id) {
        this.id = id;
        this.dateIncident = dateIncident;
        this.score = score;
        this.typeIncident = typeIncident;
        this.echeance_id = echeance_id;
    }

    public Incident(LocalDateTime dateIncident, Integer score, StatutPaiement typeIncident, Integer echeance_id) {
        this.dateIncident = dateIncident;
        this.score = score;
        this.typeIncident = typeIncident;
        this.echeance_id = echeance_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateIncident() {
        return dateIncident;
    }

    public void setDateIncident(LocalDateTime dateIncident) {
        this.dateIncident = dateIncident;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public StatutPaiement getTypeIncident() {
        return typeIncident;
    }

    public void setTypeIncident(StatutPaiement typeIncident) {
        this.typeIncident = typeIncident;
    }

    public Integer getEcheance_id() {
        return echeance_id;
    }

    public void setEcheance_id(Integer echeance_id) {
        this.echeance_id = echeance_id;
    }
}
