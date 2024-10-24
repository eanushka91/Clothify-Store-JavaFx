package edu.icet.repository.custom;

import edu.icet.model.Inventory;
import edu.icet.repository.CrudDao;

public interface InventoryDao extends CrudDao<Inventory> {
    String findLastID();
}
