package edu.icet.repository.custom.impl;


import edu.icet.model.Supplier;
import edu.icet.repository.custom.SupplierDao;
import edu.icet.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(Supplier supplier) {
        String SQL = "INSERT INTO supplier VALUES (?, ?, ?, ?, ?)";
        try {
            return CrudUtil.execute(SQL,
                    supplier.getId(),
                    supplier.getName(),
                    supplier.getCompany(),
                    supplier.getEmailaddress(),
                    supplier.getItem()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Supplier supplier) {
        String SQL = "UPDATE supplier SET Name = ?, company = ?, email_address = ?, item = ? WHERE id=?";
        try {
            return CrudUtil.execute(SQL,
                    supplier.getName(),
                    supplier.getCompany(),
                    supplier.getEmailaddress(),
                    supplier.getItem(),
                    supplier.getId()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        String SQL = "DELETE FROM supplier WHERE id = ?";
        try {
            return CrudUtil.execute(SQL,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Supplier search(String id) {
        String SQL = "SELECT * FROM supplier WHERE id = ?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL,id);
            if (resultSet.next()) {
                return new Supplier(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ObservableList<Supplier> getAll() {
        ObservableList<Supplier> customerObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM supplier";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()) {
                customerObservableList.add(new Supplier(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("company"),
                        resultSet.getString("email_address"),
                        resultSet.getString("item")
                ));
            }
            return customerObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String findLastID() {
        String SQL = "SELECT MAX(id) FROM supplier";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
