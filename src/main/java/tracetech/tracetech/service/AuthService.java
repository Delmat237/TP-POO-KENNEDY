package tracetech.tracetech.service;

import java.sql.SQLException;

import tracetech.tracetech.dao.UserDaoImpl;
import tracetech.tracetech.model.User;
import tracetech.tracetech.security.PasswordUtil;

public class AuthService {
    private final UserDaoImpl userDao;

    public AuthService() throws SQLException {
        this.userDao = new UserDaoImpl();
    }
    
    public void register(User user, String rawPassword) throws SQLException {
        // Vérifier l'unicité
        if(userDao.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Nom d'utilisateur existe déjà");
        }
        
        //generation du sel de l'utilisateur
        // et hachage du mot de passe
        // avec le sel généré
        String salt = PasswordUtil.generateSalt();
        String hashedPassword = PasswordUtil.hashPassword(rawPassword, salt);
        
        user.setPasswordHash(hashedPassword);
        user.setSalt(salt);

        //enregistrement de l'utilisateur
        // dans la base de données
        userDao.create(user);
    }

    public User login(String username, String rawPassword) {
        //recherche de l'utilisateur
        User user = userDao.findByUsername(username);
        if(user == null) return null;
        
        String calculatedHash = PasswordUtil.hashPassword(rawPassword, user.getSalt());
        if(calculatedHash.equals(user.getPasswordHash())) {
            // Authentification réussie
            // On peut retourner l'utilisateur ou un token d'authentification
            return user;
        }
        return null;
    }

    public UserDaoImpl getUserDao() {
        return userDao;
    }
}
