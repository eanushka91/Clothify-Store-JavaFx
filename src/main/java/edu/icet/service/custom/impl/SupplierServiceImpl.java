package edu.icet.service.custom.impl;

import edu.icet.repository.Daofactory;
import edu.icet.model.Supplier;
import edu.icet.repository.custom.SupplierDao;
import edu.icet.service.custom.SupplierService;
import edu.icet.util.DaoType;
import javafx.collections.ObservableList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierServiceImpl implements SupplierService {
    SupplierDao supplierDao = Daofactory.getInstance().getDaoType(DaoType.Supplier);
    @Override
    public boolean add(Supplier supplier) {
        return supplierDao.save( supplier);
    }

    @Override
    public boolean delete(String id) {
        return supplierDao.delete(id);
    }

    @Override
    public Supplier search(String id) {
        return supplierDao.search(id);
    }

    @Override
    public boolean update(Supplier supplier) {
        return supplierDao.update(supplier);
    }

    @Override
    public String generateSupplierId() {
        String lastId = supplierDao.findLastID();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(lastId);
        return (matcher.find()) ? matcher.group() : null;
    }

    @Override
    public ObservableList<Supplier> getAllSupplier() {
        return supplierDao.getAll();
    }
}
