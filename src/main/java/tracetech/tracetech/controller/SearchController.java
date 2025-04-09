package tracetech.tracetech.controller;

import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import tracetech.tracetech.model.StolenItem;
import tracetech.tracetech.service.StolenItemService;

public class SearchController  {

    @FXML private TextField imeiField;
    @FXML private TextField serialNumberField;
    @FXML private TextField descriptionField;
    @FXML private ListView<String> resultsListView;
    @FXML private ComboBox<String> typeFilterComboBox;
    @FXML private ComboBox<String> dateFilterComboBox;

    private StolenItemService stolenItemService ;
    private ObservableList<StolenItem> searchResults = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialisation des filtres
        typeFilterComboBox.setItems(FXCollections.observableArrayList("Tous", "Téléphone", "Ordinateur", "Autre"));
        typeFilterComboBox.getSelectionModel().selectFirst();

        dateFilterComboBox.setItems(FXCollections.observableArrayList("Toutes", "Dernière semaine", "Dernier mois", "Dernière année"));
        dateFilterComboBox.getSelectionModel().selectFirst();

        try {
            stolenItemService = new StolenItemService();
        } catch (SQLException e) {
            
        }
    }

    @FXML
    public void handleSearch() {
        // Récupérer les critères de recherche
        String imei = imeiField.getText();
        String serialNumber = serialNumberField.getText();
        String description = descriptionField.getText();

        // Effectuer la recherche
        List<StolenItem> results = stolenItemService.searchItems(imei, serialNumber, description);

        // Mettre à jour la liste des résultats
        if (results == null || results.isEmpty()) {
            resultsListView.setItems(FXCollections.observableArrayList("Aucun résultat trouvé"));
        } else if (results.size() > 10) {
            resultsListView.setItems(FXCollections.observableArrayList("Trop de résultats, veuillez affiner votre recherche"));
        } else {
            System.out.println("objets trouvé");
            searchResults.setAll(results);
            updateResultsListView();
        }
      
    }

    @FXML
    public void applyFilters() {
        // Récupérer les filtres sélectionnés
        String selectedType = typeFilterComboBox.getSelectionModel().getSelectedItem();
        String selectedDate = dateFilterComboBox.getSelectionModel().getSelectedItem();

        // Appliquer les filtres aux résultats existants
        List<StolenItem> filteredResults = stolenItemService.filterItems(searchResults, selectedType, selectedDate);

        // Mettre à jour la liste des résultats filtrés
        searchResults.setAll(filteredResults);
        updateResultsListView();
    }

    private void updateResultsListView() {
        ObservableList<String> displayResults = FXCollections.observableArrayList();

        for (StolenItem item : searchResults) {
            String result = String.format("Type: %s | Marque: %s | Modèle: %s | Statut: %s | Date: %s",
                    item.getType(),
                    item.getMark(),
                    item.getModel(),
                    item.isState() ? "Retrouvé" : "Volé",
                    item.getReportDate());
            displayResults.add(result);
        }

        resultsListView.setItems(displayResults);
    }
}
