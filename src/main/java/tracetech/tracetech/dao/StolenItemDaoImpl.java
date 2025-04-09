package tracetech.tracetech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tracetech.tracetech.model.StolenItem;


public class StolenItemDaoImpl implements StolenItemDao {

       private final Connection connection;

    public StolenItemDaoImpl() throws SQLException {
        this.connection = Database.getConnection();
    }
    @Override
    public void create(StolenItem stolenItem) throws SQLException {
      final String sql = "INSERT INTO StolenItems(imei,serial_number,description,theft_date,owner_id,mark,picture_path,type,model) VALUES(?, ?, ?, ?, ?,?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, stolenItem.getImei());
            pstmt.setString(2, stolenItem.getSerialNumber());
            pstmt.setString(3, stolenItem.getDescription());
            pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(stolenItem.getTheftDate()));
            pstmt.setInt(5, stolenItem.getOwnerId());
            pstmt.setString(6, stolenItem.getMark());
            pstmt.setString(7, stolenItem.getPicturePath());
            pstmt.setString(8, stolenItem.getType());
            pstmt.setString(9, stolenItem.getModel());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création d'objet"+e.getMessage());
        }
    }

    @Override
    public StolenItem findByImei(String imei) {
       String sql = "SELECT * FROM StolenItems WHERE imei = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, imei);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToStolenItem(rs);
            }
            return null;
        } catch (SQLException e) {
           System.err.println("Erreur lors de la recherche d'utilisateur par nom d'utilisateur"+e.getMessage());
            return null;
        }
    }
    
    private StolenItem  mapResultSetToStolenItem(ResultSet rs) throws SQLException {
        StolenItem stolenItem  = new StolenItem();
        // Remplir l'objet StolenItem avec les données de la base de données
        stolenItem.setId(rs.getInt("id"));
        stolenItem.setImei(rs.getString("imei"));
        stolenItem.setType(rs.getString("type"));
        stolenItem.setModel(rs.getString("model"));
        stolenItem.setMark(rs.getString("mark"));
        stolenItem.setSerialNumber(rs.getString("serial_number"));
        stolenItem.setDescription(rs.getString("description"));
         stolenItem.setOwnerId(rs.getInt("owner_id"));
        stolenItem.setTheftDate(rs.getTimestamp("theft_date").toLocalDateTime());
        stolenItem.setReportDate(rs.getTimestamp("report_date").toLocalDateTime());
        return stolenItem;
    }

    @Override
    public void UpdateState(StolenItem stolenItem) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'UpdateState'");
    }
    
}
