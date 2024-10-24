package edu.icet.repository;

import edu.icet.repository.custom.InventoryDao;
import edu.icet.repository.custom.impl.*;
import edu.icet.util.DaoType;

public class Daofactory {
    private static Daofactory instance;
    private Daofactory(){}

    public static Daofactory getInstance() {
        return instance == null ? instance = new Daofactory() : instance;
    }

    public <T extends SuperDao> T getDaoType(DaoType type) {
        return switch (type) {
            case Employee -> (T) new EmployeeDaoImpl();
            case Product -> (T) new ProductDaoImpl();
            case Customer -> (T) new CustomerDaoImpl();
            case Supplier -> (T) new SupplierDaoImpl();
            case Inventory -> (T) new InventoryDaoImpl();

        };
    }
}