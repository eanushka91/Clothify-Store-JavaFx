package edu.icet.repository.custom.impl;

import edu.icet.model.Customer;
import edu.icet.model.Employee;
import edu.icet.repository.custom.EmployeeDao;
import edu.icet.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public boolean save(Employee employee) {
        String SQL = "INSERT INTO employee VALUES (?, ?, ?, ?, ?)";
        try {
            return CrudUtil.execute(SQL,
                    employee.getId(),
                    employee.getName(),
                    employee.getContact(),
                    employee.getCompany(),
                    employee.getAddress()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Employee employee) {
        String SQL = "UPDATE employee SET Name = ?, contactno = ?, company = ?, address = ? WHERE id=?";
        try {
            return CrudUtil.execute(SQL,
                    employee.getName(),
                    employee.getContact(),
                    employee.getAddress(),
                    employee.getCompany(),
                    employee.getId()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        String SQL = "DELETE FROM employee WHERE id = ?";
        try {
            return CrudUtil.execute(SQL,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee search(String id) {
        String SQL = "SELECT * FROM employee WHERE id = ?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL,id);
            if (resultSet.next()) {
                return new Employee(
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
    public ObservableList getAll() {
        ObservableList<Employee> customerObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM employee";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()) {
                customerObservableList.add(new Employee(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("contactno"),
                        resultSet.getString("company"),
                        resultSet.getString("address")
                ));
            }
            return customerObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String findLastID() {
        String SQL = "SELECT MAX(id) FROM employee";
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
