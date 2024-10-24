package edu.icet.service.custom;

import edu.icet.model.Supplier;
import edu.icet.service.SuperService;
import javafx.collections.ObservableList;

public interface SupplierService extends SuperService {
    boolean add(Supplier supplier);

    boolean delete(String id);

    Supplier search(String supplier);

    boolean update(Supplier supplier);

    String generateSupplierId();

    ObservableList<Supplier> getAllSupplier();
}
