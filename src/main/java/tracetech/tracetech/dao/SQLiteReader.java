package tracetech.tracetech.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteReader {
    public static void main(String[] args) {
        // Chemin vers la base de données SQLite
        String url = "jdbc:sqlite:traceTech_db";

        // Requête SQL
        String query = "SELECT * FROM StolenItems";
        String query2 = "SELECT * FROM Users";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

                System.out.println("=======================liste sTOLEN==========================");
            // Parcourir les résultats
            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String mark = rs.getString("mark");
                String model = rs.getString("model");
                String imei = rs.getString("imei");
                String serialNumber = rs.getString("serial_number");
                String description = rs.getString("description");
                boolean state = rs.getBoolean("state");

                System.out.printf("ID: %d, Type: %s, Marque: %s, Modèle: %s, IMEI: %s, Numéro de série: %s, Description: %s, Retrouvé: %b%n",
                        id, type, mark, model, imei, serialNumber, description, state);
            }

            ResultSet rs2= stmt.executeQuery(query2);
        
            System.out.println("=======================liste user==========================");
                // Parcourir les résultats
                while (rs2.next()) {
                    int id = rs2.getInt("id");
                    String username = rs2.getString("username");
                    String email = rs2.getString("email");
    
                    System.out.printf("ID: %d, Nom d'utilisateur: %s, Email: %s%n", id, username, email);
                }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

         
               
        
    }
}
