package edu.icet.service.custom.impl;


import edu.icet.model.Inventory;
import edu.icet.repository.Daofactory;

import edu.icet.repository.custom.InventoryDao;
import edu.icet.service.custom.InventoryService;
import edu.icet.util.DaoType;
import javafx.collections.ObservableList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InventoryServiceImpl implements InventoryService {

    InventoryDao inventoryDao = Daofactory.getInstance().getDaoType(DaoType.Inventory);

    @Override
    public boolean add(Inventory inventory) {
        return inventoryDao.save(inventory);
    }

    @Override
    public boolean delete(String id) {
        return inventoryDao.delete(id);
    }

    @Override
    public Inventory search(String id) {
        return inventoryDao.search(id);
    }

    @Override
    public boolean update(Inventory inventory) {
        return inventoryDao.update(inventory);
    }

    @Override
    public String generateProductId() {
        String lastId = inventoryDao.findLastID();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(lastId);
        return (matcher.find()) ? matcher.group() : null;
    }

    @Override
    public ObservableList<Inventory> getAllProduct() {
        return inventoryDao.getAll();
    }
}
