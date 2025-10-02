package main.model;

import main.enums.EnumDecision;
import main.enums.EnumRole;
import main.enums.EnumSitFam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Person {
    protected Integer id;
    protected String nom;
    protected String prenom;
    protected String email;
    protected LocalDate dateNaissance;
    protected String ville;
    protected Integer nombreEnfants;
    protected Boolean investissement;
    protected Boolean placement;
    protected EnumSitFam situationFamiliale;
    protected LocalDateTime createdAt;
    protected Integer score;
    protected EnumRole role;

    public Person(Integer id, String nom, String prenom, String email, LocalDate dateNaissance, String ville, Integer nombreEnfants, Boolean investissement, Boolean placement, EnumSitFam situationFamiliale, LocalDateTime createdAt, Integer score, EnumRole role
    ) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.nombreEnfants = nombreEnfants;
        this.investissement = investissement;
        this.placement = placement;
        this.situationFamiliale = situationFamiliale;
        this.createdAt = createdAt;
        this.score = score;
        this.role = role;
    }

    public Person(String nom, String prenom, String email, LocalDate dateNaissance, String ville, Integer nombreEnfants,
                  Boolean investissement, Boolean placement, EnumSitFam situationFamiliale, LocalDateTime createdAt, Integer score, EnumRole role
    ) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.nombreEnfants = nombreEnfants;
        this.investissement = investissement;
        this.placement = placement;
        this.situationFamiliale = situationFamiliale;
        this.createdAt = createdAt;
        this.score = score;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getNombreEnfants() {
        return nombreEnfants;
    }

    public void setNombreEnfants(Integer nombreEnfants) {
        this.nombreEnfants = nombreEnfants;
    }

    public Boolean getInvestissement() {
        return investissement;
    }

    public void setInvestissement(Boolean investissement) {
        this.investissement = investissement;
    }

    public Boolean getPlacement() {
        return placement;
    }

    public void setPlacement(Boolean placement) {
        this.placement = placement;
    }

    public EnumSitFam getSituationFamiliale() {
        return situationFamiliale;
    }

    public void setSituationFamiliale(EnumSitFam situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public EnumRole getRole() {
        return role;
    }

    public void setRole(EnumRole role) {
        this.role = role;
    }

    public static Integer calculAgePerson(LocalDate dateNaissance) {
        Period period = Period.between(dateNaissance, LocalDate.now());
        return period.getYears();
    }
}
