package main.repository.impl;

import main.model.Employe;
import main.repository.interfaces.EmployeRepository;
import main.utils.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EmployeRepositoryImp extends PersonRepositoryImpl implements EmployeRepository {
    @Override
    public Optional<Employe> inserEmploye(Employe employe) {
        String insertQuery = "INSERT INTO employe (id, salaire, anciennete, poste, typeContrat, secteur) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Employe newEmploye = (Employe) this.inserPerson(employe).orElseThrow(RuntimeException::new);
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, newEmploye.getId());
            pstmt.setDouble(2, employe.getSalaire());
            pstmt.setInt(3, employe.getAnciennete());
            pstmt.setString(4, employe.getPoste());
            pstmt.setString(5, employe.getTypeContrat());
            pstmt.setString(6, employe.getSecteur().toString());

            int rowsAff = pstmt.executeUpdate();
            if (rowsAff > 0) {
                newEmploye.setSalaire(employe.getSalaire());
                newEmploye.setAnciennete(employe.getAnciennete());
                newEmploye.setPoste(employe.getPoste());
                newEmploye.setTypeContrat(employe.getTypeContrat());
                newEmploye.setSecteur(employe.getSecteur());

                return Optional.of(newEmploye);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DatabaseException("Erreur SQL lors d'insertion du employe:" + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Employe> findEmploye(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Employe> updateEmploye(Integer id, Map<String, Object> update) {
        return Optional.empty();
    }

    @Override
    public List<Employe> selectEmployes() {
        return Collections.emptyList();
    }

    @Override
    public Boolean deleteEmploye(Employe employe) {
        return null;
    }
}
