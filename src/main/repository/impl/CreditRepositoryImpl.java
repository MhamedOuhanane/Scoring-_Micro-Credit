package main.repository.impl;

import main.config.DatabaseConfig;
import main.enums.EnumDecision;
import main.model.Credit;
import main.repository.interfaces.CreditRepository;
import main.utils.DatabaseException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CreditRepositoryImpl implements CreditRepository {
    private final Connection conn = DatabaseConfig.getInstance().getConnection();

    @Override
    public Optional<Credit> inserCredit(Credit credit) {
        String insertQuery = "INSERT INTO credit (dateCredit, montantDemande, montantOctroye, tauxInteret, dureeenMois, typeCredit, decision, person_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(credit.getDateCredit()));
            pstmt.setDouble(2, credit.getMontantDemande());
            pstmt.setDouble(3, credit.getMontantOctroye());
            pstmt.setDouble(4, credit.getTauxInteret());
            pstmt.setInt(5, credit.getDureeenMois());
            pstmt.setString(6, credit.getTypeCredit());
            pstmt.setString(7, credit.getDecision().toString());
            pstmt.setInt(8, credit.getPerson_id());

            int rowsAff = pstmt.executeUpdate();
            if (rowsAff > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        credit.setId(generatedKeys.getInt(1));
                        return Optional.of(credit);
                    }
                }
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Credit> findCredit(Integer id) {
        String findQurey = "SELECT * FROM credit WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(findQurey)) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                Integer creditId = resultSet.getInt("id");
                LocalDateTime dateCredit = resultSet.getTimestamp("dateCredit").toLocalDateTime();
                Double montantDemande = resultSet.getDouble("montantDemande");
                Double montantOctroye = resultSet.getDouble("montantOctroye");
                Double tauxInteret = resultSet.getDouble("tauxInteret");
                Integer dureeenMois = resultSet.getInt("dureeenMois");
                String typeCredit = resultSet.getString("typeCredit");
                EnumDecision decision = EnumDecision.valueOf(resultSet.getString("decision"));
                Integer person_id = resultSet.getInt("person_id");

                return Optional.of(new Credit(creditId, dateCredit, montantDemande, montantOctroye, tauxInteret, dureeenMois, typeCredit, decision, person_id));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Credit> updateCredit(Integer id, Map<String, Object> update) {
        return Optional.empty();
    }

    @Override
    public List<Credit> selectCredits() {
        return Collections.emptyList();
    }

    @Override
    public Boolean deleteCredit(Credit credit) {
        return null;
    }
}
