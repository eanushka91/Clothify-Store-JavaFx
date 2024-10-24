package edu.icet.service.custom;


import edu.icet.model.Inventory;
import edu.icet.model.Product;
import edu.icet.service.SuperService;
import javafx.collections.ObservableList;

public interface InventoryService extends SuperService {
    boolean add(Inventory inventory);

    boolean delete(String id);

    Inventory search(String inventory);

    boolean update(Inventory inventory);

    String generateProductId();

    ObservableList<Inventory> getAllProduct();
}
