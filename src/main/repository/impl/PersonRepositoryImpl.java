package main.repository.impl;

import main.config.DatabaseConfig;
import main.enums.EnumSitFam;
import main.model.Person;
import main.repository.interfaces.PersonRepository;
import main.utils.DatabaseException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class PersonRepositoryImpl implements PersonRepository {
    protected final Connection conn = DatabaseConfig.getInstance().getConnection();

    @Override
    public Optional<Person> inserPerson(Person person) {
        String insertQuery = "INSERT INTO person " +
                "(nom, prenom, email, dateNaissance, ville, nombreEnfants, investissement, placement, situationFamiliale, createdAt, score) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, person.getNom());
            pstmt.setString(2, person.getPrenom());
            pstmt.setString(3, person.getEmail());
            pstmt.setDate(4, Date.valueOf(person.getDateNaissance()));
            pstmt.setString(5, person.getVille());
            pstmt.setInt(6, person.getNombreEnfants());
            pstmt.setBoolean(7, person.getInvestissement());
            pstmt.setBoolean(8, person.getPlacement());
            pstmt.setString(9, person.getSituationFamiliale().toString());
            pstmt.setTimestamp(10, Timestamp.valueOf(person.getCreatedAt()));
            pstmt.setInt(11, person.getScore());

            int rowsAff = pstmt.executeUpdate();
            if (rowsAff > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        person.setId(generatedKeys.getInt(1));
                    }
                }
                return Optional.of(person);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Person> findPerson(Integer person_id) {
        String findQuery = "SELECT * FROM person WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(findQuery)) {
            pstmt.setInt(1, person_id);

            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                LocalDate dateNaissance = resultSet.getDate("dateNaissance").toLocalDate();
                String ville = resultSet.getString("ville");
                Integer nombreEnfants = resultSet.getInt("nombreEnfants");
                Boolean investissement = resultSet.getBoolean("investissement");
                Boolean placement = resultSet.getBoolean("placement");
                EnumSitFam situationFamiliale = EnumSitFam.valueOf(resultSet.getString("situationFamiliale"));
                LocalDateTime createdAt = resultSet.getTimestamp("createdAt").toLocalDateTime();
                Integer score = resultSet.getInt("score");

                Person person = new Person(id, nom, prenom, email, dateNaissance, ville, nombreEnfants, investissement, placement, situationFamiliale, createdAt, score);
                return Optional.of(person);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public Boolean deletePerson(Person person) {
        String deleteQuery = "DELETE FROM person WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
            pstmt.setInt(1, person.getId());
            int rowsAff = pstmt.executeUpdate();
            return rowsAff > 0;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage(), e);
        }
    }
}
