package tracetech.tracetech.dao;

import java.sql.SQLException;

import tracetech.tracetech.model.User;


/*
 * Gestion de l'accès aux données utilisateur
 * role
 * 

    Isoler la couche métier des opérations de base de données

    Centraliser toutes les opérations CRUD sur la table Users

    Garantir une sécurité cohérente des données

 */
public interface UserDao {
    void create(User user) throws SQLException;
    User findByUsername(String username);
    User findByEmail(String email);
    void updatePassword(User user);
    // Autres méthodes selon besoin
}


