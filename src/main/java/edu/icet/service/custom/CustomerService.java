package edu.icet.service.custom;


import edu.icet.model.Customer;
import edu.icet.service.SuperService;
import javafx.collections.ObservableList;


public interface CustomerService extends SuperService {
    boolean addCustomer(Customer customer);

    boolean deleteCustomer(String customerId);

    Customer searchCustomer(String search);

    boolean updateCustomer(Customer customer);

    String generateCustomerId();

    ObservableList<Customer> getAllCustomers();
}