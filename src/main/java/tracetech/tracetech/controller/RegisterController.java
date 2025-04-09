package tracetech.tracetech.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tracetech.tracetech.LoadView;
import tracetech.tracetech.model.User;
import tracetech.tracetech.service.AuthService;  

public class RegisterController extends LoadView { 
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField passwordField;
    @FXML private TextField confirmPasswordField;
    @FXML private Label errorMessage;
   
    @FXML
    public void handleRegister(ActionEvent event) {  // Ajout de ActionEvent
        // Récupération des informations de l'utilisateur
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Vérification du contenu
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            errorMessage.setText("All fields are required.");
            System.out.println("All fields are required.");
            return;
        }
        if (!email.matches("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$")) {
            errorMessage.setText("Invalid email format.");
            System.out.println("Invalid email format.");
            return;
        }
        if (!phone.matches("^[+]237[2-9][0-9]{7,8}$")) { //Numero camerounais pour le moment
            errorMessage.setText("Invalid phone number format.");
            System.out.println("Invalid phone number format.");
            return;
        }
        if (password.length() < 8) {
            errorMessage.setText("Password must be at least 8 characters long.");
            System.out.println("Password must be at least 8 characters long.");
            return;
        }
        if (!password.matches(".*[A-Z].*")) {
            errorMessage.setText("Password must contain at least one uppercase letter.");
            System.out.println("Password must contain at least one uppercase letter.");
            return;
        }
        if (!password.matches(".*[a-z].*")) {
            errorMessage.setText("Password must contain at least one lowercase letter.");
            System.out.println("Password must contain at least one lowercase letter.");
            return;
        }
        if (!password.matches(".*[0-9].*")) {
            errorMessage.setText("Password must contain at least one digit.");
            System.out.println("Password must contain at least one digit.");
            return;
        }
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            errorMessage.setText("Password must contain at least one special character.");
            System.out.println("Password must contain at least one special character.");
            return;
        }
        if (password.contains(name)) {
            errorMessage.setText("Password cannot contain your name.");
            System.out.println("Password cannot contain your name.");
            return;
        }
        if (password.contains(email)) {
            errorMessage.setText("Password cannot contain your email.");
            System.out.println("Password cannot contain your email.");
            return;
        }
        if (password.contains(phone)) {
            errorMessage.setText("Password cannot contain your phone number.");
            System.out.println("Password cannot contain your phone number.");
            return;
        }
        if (password.equals(confirmPassword)) {
         
      
           try {
             // Transmission des données au service d'Authentification
             AuthService authService = new AuthService();

              authService.register(new User(name, email, phone, LocalDateTime.now()) , password);

           
               // Registration successful
               System.out.println("Registration successful for " + name);
           
              loadView(event);  // Appel de la méthode loadView de LoadView
                  //Chargement de la page de login
            } catch (Exception e) {
                errorMessage.setText("Registration failed: " + e.getMessage());
                System.out.println("Registration failed: " + e.getMessage());
            }
        } else {
           errorMessage.setText("Passwords do not match.");
            System.out.println("Passwords do not match.");
            return ;
        }
    }

    private void loadLoginView(ActionEvent event) throws IOException {
          loadView(event);
    }

    @Override
    protected String getFXMLFileName() {
        return "/tracetech/tracetech/view/LoginView.fxml";  // Chemin vers le fichier LoginView.fxml
    }
    
}
