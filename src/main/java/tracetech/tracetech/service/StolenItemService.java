package tracetech.tracetech.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tracetech.tracetech.dao.StolenItemDaoImpl;
import tracetech.tracetech.model.StolenItem;

public class StolenItemService {

    private StolenItemDaoImpl stolenItemDao;

    public StolenItemService() throws SQLException {
        this.stolenItemDao = new StolenItemDaoImpl();
    }
   public void reportStolenItem(StolenItem stolenItem) throws SQLException {
        if(stolenItemDao.findByImei(stolenItem.getImei()) != null) {
            
            throw new IllegalArgumentException("Nom d'utilisateur existe déjà");
            
        }else {

            //ICreer l'objet StolenItem
            stolenItemDao.create(stolenItem);
            
        }
        
    }
       
    public List<StolenItem> searchItems(String imei, String serialNumber, String description) {
        List<StolenItem> results = new ArrayList<>();

        //pour le moment je recherche avec l'imei seulement
        StolenItem item = stolenItemDao.findByImei(imei);
        if (item != null) {
            if ((imei != null && !imei.isEmpty() && imei.equals(item.getImei())) ||
                (serialNumber != null && !serialNumber.isEmpty() && serialNumber.equals(item.getSerialNumber())) ||
                (description != null && !description.isEmpty() && item.getDescription().toLowerCase().contains(description.toLowerCase()))) {
                results.add(item);
            }
        }

        return results;
    }

    public List<StolenItem> filterItems(List<StolenItem> items, String type, String dateRange) {
        List<StolenItem> filteredItems = new ArrayList<>();

        for (StolenItem item : items) {
            boolean matchesType = type.equals("Tous") || item.getType().equalsIgnoreCase(type);
            boolean matchesDate = true; // Nous Implémenteons  la logique pour filtrer par date

            if (matchesType && matchesDate) {
                filteredItems.add(item);
            }
        }

        return filteredItems;
    }
}
