package main.service.impl;

import main.model.Credit;
import main.model.Echeance;
import main.model.Person;
import main.repository.interfaces.EcheanceRepository;
import main.repository.interfaces.PersonRepository;
import main.service.interfaces.CreditService;
import main.service.interfaces.EcheanceService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EcheanceServiceImpl implements EcheanceService {
    private final EcheanceRepository echeanceRepository;
    private final CreditService creditService;
    private final PersonRepository personRepository;

    public EcheanceServiceImpl(EcheanceRepository echeanceRepository, CreditService creditService, PersonRepository personRepository) {
        this.echeanceRepository = echeanceRepository;
        this.creditService = creditService;
        this.personRepository = personRepository;
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
            return echeanceRepository.updateEcheance(echeance, updates)
                    .orElseThrow(() -> new RuntimeException("Impossible de modifier le echeance d'id: " + echean_id));
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
    public List<Echeance> selectCreditEcheances(Integer id) {
        if (id == null) throw new  IllegalArgumentException("L'id de credit ne peut pas être null");
        try {
            Credit credit = creditService.findCredit(id);
            return echeanceRepository.selectCreditEcheances(id).stream()
                    .sorted((ech1, ech2) -> ech1.getDateEcheance().compareTo(ech2.getDateEcheance()))
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public List<Echeance> selectPersonEcheances(Integer person_id) {
        if (person_id == null) throw new  IllegalArgumentException("L'id de person ne peut pas être null");
        try {
            Person person = personRepository.findPerson(person_id).orElseThrow(RuntimeException::new);
            return creditService.getPersonCredits(person.getId()).stream()
                    .flatMap(credit -> this.selectCreditEcheances(credit.getId()).stream())
                    .sorted((ech1, ech2) -> ech1.getDateEcheance().compareTo(ech2.getDateEcheance()))
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
