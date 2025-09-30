package main.repository.impl;

import main.model.Professionnel;
import main.repository.interfaces.PersonRepository;
import main.repository.interfaces.ProfessionnelRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProfessionnelRepositoryImpl implements ProfessionnelRepository {
    private final PersonRepository personRepository = new PersonRepositoryImpl();

    @Override
    public Optional<Professionnel> inserProfessionnel(Professionnel professionnel) {
        return Optional.empty();
    }

    @Override
    public Optional<Professionnel> findProfessionnel(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Professionnel> updateProfessionnel(Integer id, Map<String, Object> updates) {
        return Optional.empty();
    }

    @Override
    public List<Professionnel> selectProfessionnels() {
        return Collections.emptyList();
    }

    @Override
    public Boolean deleteProfessionnel(Professionnel professionnel) {
        return null;
    }
}
