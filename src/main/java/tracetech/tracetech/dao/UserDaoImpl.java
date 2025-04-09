package tracetech.tracetech.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tracetech.tracetech.model.User;

public class UserDaoImpl implements UserDao {
    private final Connection connection;

    public UserDaoImpl() throws SQLException {
        this.connection = Database.getConnection();
    }

    @Override
    public void create(User user) throws SQLException {
        final String sql = "INSERT INTO Users(username, password_hash, salt, email, phone) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPasswordHash());
            pstmt.setString(3, user.getSalt());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création d'utilisateur"+e.getMessage());
        }
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
            return null;
        } catch (SQLException e) {
           System.err.println("Erreur lors de la recherche d'utilisateur par nom d'utilisateur"+e.getMessage());
            return null;
        }
    }
    
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        // Remplir l'objet User avec les données de la base de données
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPasswordHash(rs.getString("password_hash"));
        user.setSalt(rs.getString("salt"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
        return user;
    }

    @Override
    public User findByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    @Override
    public void updatePassword(User user) {
        String sql = "UPDATE Users SET password_hash = ?, salt = ? WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getPasswordHash());
            pstmt.setString(2, user.getSalt());
            pstmt.setString(3, user.getUsername());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur de mise à jour du mot de passe"+ e.getMessage());
        }
    }
    
}
