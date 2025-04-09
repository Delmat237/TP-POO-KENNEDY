package tracetech.tracetech.dao;

import java.sql.SQLException;

import tracetech.tracetech.model.StolenItem;

public interface  StolenItemDao {
    void create(StolenItem stolenItem) throws  SQLException;
    StolenItem findByImei(String imei);
    void UpdateState(StolenItem stolenItem) throws SQLException; //passe de volé à retrouvé
}
