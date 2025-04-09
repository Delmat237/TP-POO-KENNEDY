package tracetech.tracetech.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;  // Utiliser LocalDate pour les dates
import java.util.UUID;

import javafx.event.ActionEvent;  // Pour formatter la date
import javafx.fxml.FXML;
import javafx.scene.Node;  // Pour générer des noms de fichiers uniques
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;  // Utiliser Alert pour les messages
import javafx.scene.control.TextField;
import javafx.scene.image.Image; // Utiliser DatePicker
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import tracetech.tracetech.model.StolenItem;
import tracetech.tracetech.model.User;
import tracetech.tracetech.service.StolenItemService;

public class ItemController  {

    @FXML private TextField typeField;
    @FXML private TextField markField;
    @FXML private TextField modelField;
    @FXML private TextField serialNumberField;
    @FXML private TextField imeiField;
    @FXML private DatePicker theftDateField; // Utiliser DatePicker
    @FXML private TextArea descriptorField;
    @FXML private ImageView imageView;
    @FXML private Label proofOfPurchaseLabel;

    private File selectedImageFile;
    private File selectedProofOfPurchaseFile;

    private StolenItemService stolenItemService;

    @FXML
    private void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner une image de l'objet");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images Files", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            selectedImageFile = selectedFile;
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
        }
    }

    @FXML
    private void chooseProofOfPurchase(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner une preuve d'achat");
        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            selectedProofOfPurchaseFile = selectedFile;
            proofOfPurchaseLabel.setText(selectedFile.getName());
        }
    }

    @FXML
    public void handleReportTheftStolenItem(ActionEvent event){
        try {
            //Récupération des informations
            String type = typeField.getText();
            String mark = markField.getText();
            String model = modelField.getText();
            String serialNumber = serialNumberField.getText();
            String imei = imeiField.getText();
            LocalDate theftDate = theftDateField.getValue();
            String description = descriptorField.getText();

            //Vérification des champs obligatoires
            if (type == null || type.isEmpty() || mark == null || mark.isEmpty() || model == null || model.isEmpty() || description == null || description.isEmpty() || theftDate == null) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Tous les champs obligatoires doivent être remplis.");
                return;
            }

            //Récupération des informations de l'utilisateur
            User user = LoginController.getUser();
            if (user == null) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Utilisateur non authentifié. Veuillez vous connecter.");
                return;
            }

            //Gestion des fichiers (images et preuves d'achat)
            String imagePath = null;
            String proofOfPurchasePath = null;

            try {
                imagePath = saveFile(selectedImageFile, "images");
                proofOfPurchasePath = saveFile(selectedProofOfPurchaseFile, "proofs");
            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la sauvegarde des fichiers : " + e.getMessage());
                return;
            }

            //Création de l'objet StolenItem
            StolenItem stolenItem = new StolenItem();
            stolenItem.setType(type);
            stolenItem.setMark(mark);
            stolenItem.setModel(model);
            stolenItem.setSerialNumber(serialNumber);
            stolenItem.setImei(imei);
            stolenItem.setTheftDate(theftDate.atStartOfDay());  // Convertir LocalDate en LocalDateTime
            stolenItem.setDescription(description);
            stolenItem.setPicturePath(imagePath);
            stolenItem.setProofOfPurchasePath(proofOfPurchasePath);
            stolenItem.setOwnerId(LoginController.getUser().getId());  // Récupérer l'ID de l'utilisateur connecté
       

            try{
                stolenItemService   = new StolenItemService();  // Instance du service
                  //Enregistrement de l'objet volé
                stolenItemService.reportStolenItem(stolenItem);
                //Affichage d'un message de confirmation sur le GUI
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Objet volé signalé avec succès.");

                // Réinitialiser les champs après le signalement
                clearFields();
            
            }catch(SQLException e){
                showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur s'est produite lors du signalement de l'objet volé : " + e.getMessage());
                e.printStackTrace();  // Log pour le débogage
            }
            
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur s'est produite lors du signalement de l'objet volé : " + e.getMessage());
            e.printStackTrace();  // Log pour le débogage
        }
    }

    private String saveFile(File file, String directory) throws IOException {
        if (file == null) {
            return null; // Pas de fichier à sauvegarder
        }

        // Créer le répertoire s'il n'existe pas
        Path dirPath = Paths.get(directory);
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        // Générer un nom de fichier unique
        String fileName = UUID.randomUUID().toString() + "_" + file.getName();
        Path filePath = Paths.get(directory, fileName);

        // Copier le fichier
        Files.copy(file.toPath(), filePath);

        return filePath.toString();  // Retourner le chemin du fichier
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        typeField.clear();
        markField.clear();
        modelField.clear();
        serialNumberField.clear();
        imeiField.clear();
        theftDateField.setValue(null);
        descriptorField.clear();
        imageView.setImage(null);
        proofOfPurchaseLabel.setText("Aucun fichier sélectionné");
        selectedImageFile = null;
        selectedProofOfPurchaseFile = null;
    }


}
