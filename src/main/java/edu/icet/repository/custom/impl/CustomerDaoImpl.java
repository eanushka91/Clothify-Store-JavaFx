package edu.icet.repository.custom.impl;


import edu.icet.model.Customer;
import edu.icet.repository.custom.CustomerDao;
import edu.icet.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer customer) {
        String SQL = "INSERT INTO customers VALUES (?, ?, ?, ?)";
        try {
            return CrudUtil.execute(SQL,
                    customer.getId(),
                    customer.getName(),
                    customer.getContact(),
                    customer.getAddress()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Customer> getAll() {
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM customers";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()) {
                customerObservableList.add(new Customer(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("contactno"),
                        resultSet.getString("address")
                ));
            }
            return customerObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        String SQL = "DELETE FROM customers WHERE id = ?";
        try {
            return CrudUtil.execute(SQL,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer search(String customerId) {
        String SQL = "SELECT * FROM customers WHERE id = ?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL,customerId);
            if (resultSet.next()) {
                return new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean update(Customer customer) {
        String SQL = "UPDATE customers SET Name = ?, contactno = ?, address = ? WHERE id=?";
        try {
            return CrudUtil.execute(SQL,
                    customer.getName(),
                    customer.getContact(),
                    customer.getAddress(),
                    customer.getId()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findLastId() {
        String SQL = "SELECT MAX(id) FROM customers";
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