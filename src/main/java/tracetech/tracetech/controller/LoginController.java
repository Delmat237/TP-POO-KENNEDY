package tracetech.tracetech.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tracetech.tracetech.LoadView;  // Assurez-vous que LoadView est dans le bon package
import tracetech.tracetech.model.User;
import tracetech.tracetech.service.AuthService;

public class LoginController extends LoadView {

    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Label errorMessage;

    private static User currentUser; //enregistre l'utilisateur actuel
    
    @FXML
    public void handleLogin(ActionEvent event) {
        // Récupération des données
        String name = usernameField.getText();
        String password = passwordField.getText();

    
        try {
                // Transmission des données au service d'Authentification
               AuthService authService = new AuthService(); 

            // Si login est correct
            currentUser = authService.login(name, password) ;
            if (currentUser != null) { 
                System.out.println("Login successful.");
                loadMainView(event);  // Appel de la méthode pour charger MainView

            } else {  // Si login échoue
                errorMessage.setText("Login failed: Incorrect credentials.");
                System.out.println("Login failed: Incorrect credentials.");
            }

        } catch (Exception e) {
            errorMessage.setText("Login failed: " + e.getMessage());
            System.out.println("Login failed: " + e.getMessage());
        }
    }

    public void loadMainView(ActionEvent event) throws IOException {  //Nouvelle méthode
        setFXMLFileName("/tracetech/tracetech/view/MainView.fxml");  //Chemin vers MainView
        loadView(event); //Utilisation de loadView pour charger la vue
    }


    @FXML
    public void handleCreateAccount(ActionEvent event) throws IOException {
        setFXMLFileName("/tracetech/tracetech/view/RegisterView.fxml"); //Chemin vers RegisterView
        loadView(event);  // Appel de la méthode pour charger RegisterView
    }

     private void setFXMLFileName(String fxmlFileName) {
        this.fxmlFileName = fxmlFileName;
    }

    @FXML
    public void handleForgotPassword(){
    }

    private String fxmlFileName;

    @Override
    protected String getFXMLFileName() {
        return fxmlFileName;
    }

    public static User getUser(){
        return currentUser;
    }
}
