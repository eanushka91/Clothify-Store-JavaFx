package edu.icet.repository.custom;

import edu.icet.model.Employee;
import edu.icet.repository.CrudDao;

public interface EmployeeDao extends CrudDao<Employee> {
    String findLastID();
}
