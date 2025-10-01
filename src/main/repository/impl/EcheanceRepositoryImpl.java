package main.repository.impl;

import main.config.DatabaseConfig;
import main.enums.EnumDecision;
import main.enums.StatutPaiement;
import main.model.Credit;
import main.model.Echeance;
import main.model.Employe;
import main.model.Person;
import main.repository.interfaces.EcheanceRepository;
import main.utils.DatabaseException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class EcheanceRepositoryImpl implements EcheanceRepository {
    private final Connection conn = DatabaseConfig.getInstance().getConnection();

    @Override
    public Optional<Echeance> inserEcheance(Echeance echeance) {
        String insertQuery = "INSERT INTO echeance (dateEcheance, mensualite, datePaiement, statutPaiement, credit_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(echeance.getDateEcheance()));
            pstmt.setDouble(2, echeance.getMensualite());
            pstmt.setTimestamp(3, Timestamp.valueOf(echeance.getDatePaiement()));
            pstmt.setString(4, echeance.getStatutPaiement().toString());
            pstmt.setInt(5, echeance.getCredit_id());

            int rowsAff = pstmt.executeUpdate();
            if (rowsAff > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        echeance.setId(generatedKeys.getInt(1));
                        return Optional.of(echeance);
                    }
                }
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Echeance> findEcheance(Integer id) {
        String findQurey = "SELECT * FROM echeance WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(findQurey)) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                Integer echeanceId = resultSet.getInt("id");
                LocalDateTime dateEcheance = resultSet.getTimestamp("dateEcheance").toLocalDateTime();;
                Double mensualite = resultSet.getDouble("mensualite");;
                LocalDateTime datePaiement = resultSet.getTimestamp("datePaiement").toLocalDateTime();;
                StatutPaiement statutPaiement = StatutPaiement.valueOf(resultSet.getString("statutPaiement"));;
                Integer credit_id = resultSet.getInt("credit_id");;

                return Optional.of(new Echeance(echeanceId, dateEcheance, mensualite, datePaiement, statutPaiement, credit_id));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Echeance> updateEcheance(Integer id, Map<String, Object> update) {
        return Optional.empty();
    }

    @Override
    public List<Echeance> selectEcheances() {
        String findQurey = "SELECT * FROM echeance";
        try (PreparedStatement pstmt = conn.prepareStatement(findQurey)) {
            ResultSet resultSet = pstmt.executeQuery();
            List<Echeance> echeances = new ArrayList<>();
            while (resultSet.next()) {
                Integer echeanceId = resultSet.getInt("id");
                LocalDateTime dateEcheance = resultSet.getTimestamp("dateEcheance").toLocalDateTime();;
                Double mensualite = resultSet.getDouble("mensualite");;
                LocalDateTime datePaiement = resultSet.getTimestamp("datePaiement").toLocalDateTime();;
                StatutPaiement statutPaiement = StatutPaiement.valueOf(resultSet.getString("statutPaiement"));;
                Integer credit_id = resultSet.getInt("credit_id");;

                echeances.add(new Echeance(echeanceId, dateEcheance, mensualite, datePaiement, statutPaiement, credit_id));
            }
            return echeances;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public Boolean deleteEcheance(Echeance echeance) {
        return null;
    }

    @Override
    public List<Echeance> selectCreditEcheances(Integer id) {
        return Collections.emptyList();
    }
}
