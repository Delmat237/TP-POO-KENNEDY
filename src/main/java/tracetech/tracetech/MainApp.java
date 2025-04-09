package tracetech.tracetech;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        //CHARGEMENT DU FICHIER FXML de la page de Login

        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/LoginView.fxml"));
        Parent root = loader.load();
        //chagement de la scene
        Scene scene = new Scene(root, 800, 600);
       primaryStage.setTitle("Application de Traçage d'Objets Volés");
        primaryStage.setScene(scene);
        primaryStage.show();

        //
    }

    public static void main(String[] args) {
        launch(args);
    }
}
