package tracetech.tracetech.dao;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Database {
    private static final String DB_PROPERTIES_FILE = "db.properties";
    private static String URL;
  

    static {
        Properties props = new Properties();
        InputStream input = null;
        try {
            input = Database.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE);
            if (input == null) {
                System.err.println("Unable to find " + DB_PROPERTIES_FILE);
                throw new IOException("Unable to find " + DB_PROPERTIES_FILE);
            }
            props.load(input);
            URL = props.getProperty("db.url");
         
        } catch (IOException ex) {
            System.err.println("Erreur lors du chargement du fichier de configuration : " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Erreur lors du chargement du fichier de configuration", ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Créer la base de données et la table si elles n'existent pas
        try {
            createDatabaseAndTable();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création des  tables : " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la création des tables", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
    private static void createDatabaseAndTable() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
    
            // Création table users
            stmt.executeUpdate("""
              CREATE TABLE IF NOT EXISTS Users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username VARCHAR (50) UNIQUE NOT NULL,
                    password_hash VARCHAR (64) NOT NULL, 
                    email VARCHAR (100) UNIQUE NOT NULL,
                    phone VARCHAR (20),
                    salt VARCHAR (64) NOT NULL,
                    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP
                 )
              """);
    
            // Création table StolenItems
            stmt.executeUpdate("""
              CREATE TABLE IF NOT EXISTS StolenItems (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    imei VARCHAR (15) UNIQUE,
                    type VARCHAR(25),
                    model VARCHAR(50),
                    serial_number VARCHAR (50),
                    description TEXT NOT NULL,
                    theft_date DATETIME NOT NULL,
                    mark VARCHAR(15),
                    picture_path VARCHAR(255),
                    proov VARCHAR(255),
                    owner_id INTEGER NOT NULL,
                    state INTEGER NOT NULL CHECK(state IN (0, 1)) DEFAULT 0,
                    report_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (owner_id) REFERENCES Users (id)
                 );
              """);       
    
        }
    }
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}