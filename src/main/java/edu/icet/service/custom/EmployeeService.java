package edu.icet.service.custom;

import edu.icet.model.Employee;
import edu.icet.service.SuperService;
import javafx.collections.ObservableList;

public interface EmployeeService extends SuperService {
    boolean add(Employee employee);

    boolean delete(String id);

    Employee search(String employee);

    boolean update(Employee employee);

    String generateEmployeeId();

    ObservableList<Employee> getAllEmployee();
}
