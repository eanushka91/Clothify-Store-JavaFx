package edu.icet.service.custom.impl;

import edu.icet.model.Employee;
import edu.icet.repository.Daofactory;
import edu.icet.repository.custom.EmployeeDao;
import edu.icet.service.custom.EmployeeService;
import edu.icet.util.DaoType;
import javafx.collections.ObservableList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao = Daofactory.getInstance().getDaoType(DaoType.Employee);
    @Override
    public boolean add(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public boolean delete(String id) {
        return employeeDao.delete(id);
    }

    @Override
    public Employee search(String id) {
        return employeeDao.search(id);
    }

    @Override
    public boolean update(Employee employee) {
        employeeDao.update(employee);
        return true;
    }

    @Override
    public String generateEmployeeId() {
        String lastId = employeeDao.findLastID();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(lastId);
        return (matcher.find()) ? matcher.group() : null;
    }

    @Override
    public ObservableList<Employee> getAllEmployee() {
        return employeeDao.getAll();
    }
}
