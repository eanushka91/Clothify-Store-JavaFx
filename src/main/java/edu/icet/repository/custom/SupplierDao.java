package edu.icet.repository.custom;

import edu.icet.model.Supplier;
import edu.icet.repository.CrudDao;

public interface SupplierDao extends CrudDao<Supplier> {
    String findLastID();
}
