package edu.icet.service;

import edu.icet.repository.custom.impl.InventoryDaoImpl;
import edu.icet.service.custom.impl.*;
import edu.icet.util.ServiceType;

public class Servicefactory {
    private static Servicefactory instance;
    private Servicefactory(){}

    public static Servicefactory getInstance() {
        return instance == null ? instance = new Servicefactory() : instance;
    }

    public <T extends SuperService> T getServiceType(ServiceType type) {
        return switch (type) {
            case Employee -> (T) new EmployeeServiceImpl();
            case Product -> (T) new ProductServiceImpl();
            case Customer -> (T) new CustomerServiceImpl();
            case Supplier -> (T) new SupplierServiceImpl();
            case Inventory -> (T) new InventoryServiceImpl();
        };
    }
}