package main.service.impl;

import main.enums.StatutPaiement;
import main.model.Credit;
import main.model.Echeance;
import main.model.Incident;
import main.model.Person;
import main.repository.interfaces.EcheanceRepository;
import main.repository.interfaces.IncidentRepository;
import main.service.interfaces.CreditService;
import main.service.interfaces.EcheanceService;
import main.service.interfaces.PersonService;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EcheanceServiceImpl implements EcheanceService {
    private final EcheanceRepository echeanceRepository;
    private final CreditService creditService;
    private final PersonService personService;
    private final IncidentRepository incidentRepository;

    public EcheanceServiceImpl(EcheanceRepository echeanceRepository, CreditService creditService, PersonService personService, IncidentRepository incidentRepository) {
        this.echeanceRepository = echeanceRepository;
        this.creditService = creditService;
        this.personService = personService;
        this.incidentRepository = incidentRepository;
    }

    @Override
    public Echeance ajouterEcheance(Echeance echeance) {
        if (echeance == null) throw new IllegalArgumentException("Les information de echeance ne peut pas être null");
        try {
            return echeanceRepository.insertEcheance(echeance)
                    .orElseThrow(() -> new RuntimeException("Impossible d'ajouter d'echeance"));
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public Echeance findEcheance(Integer id) {
        if (id == null) throw new  IllegalArgumentException("L'id echeance ne peut pas être null");
        try {
            return echeanceRepository.findEcheance(id)
                    .orElseThrow((() -> new RuntimeException("Aucun echeance trouvé avec l'id: " + id)));
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public Echeance updateEcheance(Integer echean_id, Map<String, Object> updates) {
        if (echean_id == null) throw new  IllegalArgumentException("L'id echeance ne peut pas être null");
        if (updates.isEmpty()) throw new  RuntimeException("Les modifications ne peut pas être vide");
        try {
            Echeance echeance = this.findEcheance(echean_id);
            echeance = echeanceRepository.updateEcheance(echeance, updates)
                    .orElseThrow(() -> new RuntimeException("Impossible de modifier le echeance d'id: " + echean_id));

            if (updates.containsKey("datePaiement") && updates.containsKey("statutPaiement")) {
                Person person = this.personService.findPerson(this.creditService.findCredit(echeance.getCredit_id()).getPerson_id());
                int score = person.getScore();
                if (updates.get("statutPaiement").equals(StatutPaiement.PAYEENRETARD)) score += 3;
                if (updates.get("statutPaiement").equals(StatutPaiement.IMPAYEREGLE)) score += 5;

                score = score < 0 ? 0 : Math.min(score, 100);
                Map<String , Object> update = new HashMap<>();
                update.put("score", score);
                person = personService.updatePerson(person.getId(), update);
            }
            return echeance;
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public List<Echeance> getAllEcheances() {
        try {
            return echeanceRepository.selectEcheances().stream()
                    .sorted((ech1, ech2) -> {
                        return ech1.getDateEcheance().compareTo(ech2.getDateEcheance());
                    })
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public Boolean deleteEcheance(Integer echeance_id) {
        if (echeance_id == null) throw new  IllegalArgumentException("L'id d'echeance ne peut pas être null");
        try {
            Echeance echeance = this.findEcheance(echeance_id);
            return echeanceRepository.deleteEcheance(echeance);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public List<Echeance> selectPersonEcheances(Integer person_id) {
        if (person_id == null) throw new  IllegalArgumentException("L'id de person ne peut pas être null");
        try {
            Person person = personService.findPerson(person_id);
            return creditService.getPersonCredits(person.getId()).stream()
                    .flatMap(credit -> this.creditService.selectCreditEcheances(credit.getId()).stream())
                    .sorted((ech1, ech2) -> ech1.getDateEcheance().compareTo(ech2.getDateEcheance()))
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void traitementEcheance() {
        try {
            LocalDateTime now = LocalDateTime.now();
            List<Echeance> echeances = this.getAllEcheances();
            echeances.stream()
                    .filter(echeance -> echeance.getDateEcheance().isBefore(now.minusDays(5)))
                    .filter(echeance -> !echeance.getStatutPaiement().equals(StatutPaiement.IMPAYEREGLE)
                        && !echeance.getStatutPaiement().equals(StatutPaiement.PAYEATEMPS)
                        && !echeance.getStatutPaiement().equals(StatutPaiement.PAYEENRETARD))
                    .forEach(echeance -> {
                        Person person = this.personService.findPerson(this.creditService.findCredit(echeance.getCredit_id()).getPerson_id());
                        int score = person.getScore();
                        StatutPaiement statutPaiement = echeance.getStatutPaiement();
                        if (echeance.getDateEcheance().isAfter(now.minusDays(31))) {
                            if (echeance.getStatutPaiement().equals(StatutPaiement.PENDING)) {
                                statutPaiement = StatutPaiement.ENRETARD;
                                score -= 3;
                            }
                        } else {
                            statutPaiement = StatutPaiement.IMPAYENONREGLE;
                            score -= 10;
                        }

                        if (!echeance.getStatutPaiement().equals(statutPaiement)) {
                            Map<String, Object> updates = new HashMap<>();
                            updates.put("statutPaiement", statutPaiement.toString());
                            this.updateEcheance(echeance.getId(), updates);
                            Incident incident = new Incident(LocalDateTime.now(), score, statutPaiement, echeance.getId());
                            incident = incidentRepository.insertIncident(incident)
                                    .orElseThrow(() -> new RuntimeException("Impossible d'ajouter d'incident"));
                            Map<String , Object> update = new HashMap<>();
                            score = score < 0 ? 0 : Math.min(score, 100);
                            update.put("score", score);
                            person = personService.updatePerson(person.getId(), update);
                        }
                    });
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
