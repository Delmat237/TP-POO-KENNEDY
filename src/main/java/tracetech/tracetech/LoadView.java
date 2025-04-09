package tracetech.tracetech;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class LoadView {

    protected abstract String getFXMLFileName();//nom du fichier fxml

    protected void loadView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(getFXMLFileName()));
        Parent view = loader.load();

        Scene scene = new Scene(view, 800, 600);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
