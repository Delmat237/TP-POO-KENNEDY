package tracetech.tracetech.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import tracetech.tracetech.LoadView;

public class MainViewController extends LoadView { //Etends LoadView pour réutiliser le code
    @FXML private VBox contentArea;
    private String fxmlFileName;

    @FXML
    public void handleLogout(ActionEvent event) throws IOException {
        //TODO Code pour déconnecter l'utilisateur
        System.out.println("Déconnexion...");
        setFXMLFileName("/tracetech/tracetech/view/LoginView.fxml"); //Chemin vers LoginView
        loadView(event); //Utilisation de loadView pour charger la vue
    }

    @FXML
    public void handleReportTheft(ActionEvent event) throws IOException {
        setFXMLFileName("/tracetech/tracetech/view/ReportTheftView.fxml"); //Chemin vers ReportTheftView
        loadView(event); //Utilisation de loadView pour charger la vue
    }

    @FXML
    public void handleSearch(ActionEvent event) throws IOException {
         setFXMLFileName("/tracetech/tracetech/view/SearchView.fxml"); //Chemin vers SearchView
        loadView(event); //Utilisation de loadView pour charger la vue
    }

    @FXML
    public void handleMyProfile(ActionEvent event) {
        //TODO Code pour afficher le profil de l'utilisateur
        System.out.println("Afficher le profil...");
    }

    
    private void setFXMLFileName(String fxmlFileName) {
        this.fxmlFileName = fxmlFileName;
    }
    
    @Override
    protected String getFXMLFileName() {
        return fxmlFileName;
    }
}
