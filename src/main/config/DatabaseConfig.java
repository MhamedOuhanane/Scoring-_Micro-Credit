package main.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static DatabaseConfig instance;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/microcredit";
    private static final String USERNAME = "Mhamed";
    private static final String PASSWORD = "";

    private DatabaseConfig() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("✅ Connecté à la base de données avec succès !");
        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de la connection au base de donnee: " + e.getMessage());
        }
    }

    public static DatabaseConfig getInstance() {
        if (instance == null) instance = new DatabaseConfig();
        return instance;
    }

    public Connection getConnection() { return connection; }
}
