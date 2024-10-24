package edu.icet.repository.custom.impl;

import edu.icet.model.Inventory;
import edu.icet.repository.custom.InventoryDao;
import edu.icet.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDaoImpl implements InventoryDao {

    @Override
    public boolean delete(String id) {
        String SQL = "DELETE FROM product WHERE id = ?";
        try {
            return CrudUtil.execute(SQL,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save(Inventory inventory) {
        String SQL = "INSERT INTO product VALUES (?, ?, ?, ?, ?, ?)";
        try {
            return CrudUtil.execute(SQL,
                    inventory.getId(),
                    inventory.getName(),
                    inventory.getCategory(),
                    inventory.getSize(),
                    inventory.getPrice(),
                    inventory.getCategory()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList getAll() {
        ObservableList<Inventory> customerObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM product";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()) {
                customerObservableList.add(new Inventory(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("category"),
                        resultSet.getString("size"),
                        resultSet.getString("price"),
                        resultSet.getString("quantity")
                ));
            }
            return customerObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Inventory search(String id) {
        String SQL = "SELECT * FROM product WHERE id = ?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL,id);
            if (resultSet.next()) {
                return new Inventory(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean update(Inventory inventory) {
        String SQL = "UPDATE product SET Name = ?, category = ?, size = ?, price = ?, quantity = ? WHERE id=?";
        try {
            return CrudUtil.execute(SQL,
                    inventory.getId(),
                    inventory.getName(),
                    inventory.getCategory(),
                    inventory.getSize(),
                    inventory.getPrice(),
                    inventory.getCategory()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String findLastID() {
        String SQL = "SELECT MAX(id) FROM product";
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
