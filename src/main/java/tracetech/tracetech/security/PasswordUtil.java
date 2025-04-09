package tracetech.tracetech.security;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int SALT_LENGTH = 16;
    
    public static String generateSalt() {
        /*
         * Methode permettant d egenerer le sel d'un utilisateur
         */
        byte[] salt = new byte[SALT_LENGTH];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String password, String salt) {
        /*
         * Hachage du mot de passe avec l'algorithme SHA-256
         * et le sel généré aléatoirement.
         * Le mot de passe et le sel sont convertis en tableau d'octets
         */
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Erreur de hachage", e);
        }
    }
}
