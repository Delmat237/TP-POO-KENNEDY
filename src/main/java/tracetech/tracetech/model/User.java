package tracetech.tracetech.model;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String username;
    private String email;
    private String passwordHash;
    private String salt; //variable permettant de stocker le sel qui permettra de hacher le mot de passe
    private String phone;
    private LocalDateTime creationDate;

    public User(String username, String email, String phone, LocalDateTime creationDate) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.creationDate = creationDate;
    }
    public User() {
        //TODO Auto-generated constructor stub
    }
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    @Override
    public String toString() {
        return "User{" +
               "id='" + id+ '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", phone='" + phone + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

 
    
}
