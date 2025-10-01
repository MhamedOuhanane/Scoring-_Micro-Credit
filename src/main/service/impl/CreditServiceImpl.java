package main.service.impl;

import main.enums.EnumDecision;
import main.model.Credit;
import main.model.Person;
import main.repository.interfaces.CreditRepository;
import main.repository.interfaces.EmployeRepository;
import main.repository.interfaces.PersonRepository;
import main.repository.interfaces.ProfessionnelRepository;
import main.service.interfaces.CreditService;
import main.service.interfaces.EmployeService;
import main.service.interfaces.ProfessionnelService;
import main.utils.DatabaseException;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;
    private final PersonRepository personRepository;
    private final EmployeService employeService;
    private final ProfessionnelService professionnelService;

    public CreditServiceImpl(CreditRepository creditRepository, PersonRepository personRepository, EmployeService employeService, ProfessionnelService professionnelService) {
        this.creditRepository = creditRepository;
        this.personRepository = personRepository;
        this.employeService = employeService;
        this.professionnelService = professionnelService;
    }

    @Override
    public Credit ajouterCredit(Credit credit) {
        if (credit == null) throw new IllegalArgumentException("Les information de credit ne peut pas être null");
        try {
            Person person = personRepository.findPerson(credit.getPerson_id()).orElseThrow(RuntimeException::new);
            Double salaire = this.touverSalaire(person.getRole().getDescription(), credit.getPerson_id());
            List<Credit> credits = this.getAllCredits().stream()
                    .filter(credit1 -> credit1.getDecision().equals(EnumDecision.REFUS_AUTOMATIQUE))
                    .collect(Collectors.toList());
            String existence = credits.isEmpty() ? "nouveau" : "existant";
            Integer score = person.getScore();
            switch (existence) {
                case "nouveau":
                    if (score >= 70) {
                        if (credit.getMontantDemande() > 4 * salaire) credit.setMontantOctroye(4 * salaire);
                        credit.setDecision(EnumDecision.ETUDEMANUELLE);
                    } else  {
                        credit.setDecision(EnumDecision.REFUS_AUTOMATIQUE);
                    }
                    break;

                case "existant":
                    if (score >= 80) {
                        credit.setDecision(EnumDecision.ACCORDIMMEDIAT);
                        if (credit.getMontantDemande() > 10 * salaire) {
                            credit.setMontantOctroye(10 * salaire);
                            credit.setDecision(EnumDecision.ETUDEMANUELLE);
                        }
                    } else if (score >= 60) {
                        if (credit.getMontantDemande() > 7 * salaire) credit.setMontantOctroye(7 * salaire);
                        credit.setDecision(EnumDecision.ETUDEMANUELLE);
                    } else {
                        credit.setDecision(EnumDecision.REFUS_AUTOMATIQUE);
                    }
                    break;
            }

            credit.generatedDureeMois(credit.getMontantOctroye());

            return creditRepository.insertCredit(credit).orElseThrow(RuntimeException::new);
        } catch (RuntimeException e) {
            throw new DatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public Credit findCredit(Integer id) {
        return null;
    }

    @Override
    public Credit updateCredit(Integer id, Map<String, Object> update) {
        return null;
    }

    @Override
    public List<Credit> getAllCredits() {
        try {
            return creditRepository.selectCredits().stream()
                    .sorted((cd1, cd2) -> {
                        return cd1.getDateCredit().compareTo(cd2.getDateCredit());
                    })
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new DatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public Boolean deleteCredit(Integer id) {
        return null;
    }

    @Override
    public List<Credit> getPersonCredits(Integer person_id) {
        return Collections.emptyList();
    }

    private Double touverSalaire(String role, Integer person_id) {
        try {
            if (role.equals("employé")) {
                return this.employeService.findEmploye(person_id).getSalaire();
            } else if (role.equals("professionnel")) {
                return this.professionnelService.findProfessionnel(person_id).getRevenu();
            }
            return 0.;
        } catch (RuntimeException e) {
            throw new DatabaseException(e.getMessage(), e);
        }
    }
}
