package edu.icet.controller.common;

import edu.icet.model.Customer;
import edu.icet.service.Servicefactory;
import edu.icet.service.custom.CustomerService;
import edu.icet.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    @FXML
    private TextField txtFieldContactNo;

    @FXML
    private TextField txtfieldAddress;

    @FXML
    private TextField txtfieldCustomerId;

    @FXML
    private TextField txtfieldName;


    CustomerService customerService = Servicefactory.getInstance().getServiceType(ServiceType.Customer);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Customer customer = new Customer(
                txtfieldCustomerId.getText(),
                txtfieldName.getText(),
                txtFieldContactNo.getText(),
                txtfieldAddress.getText()
        );

        if (isValidCustomerInputDetails(customer)) {
            if (customerService.addCustomer(customer)) {
                new Alert(Alert.AlertType.INFORMATION, "Customer added successful !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer not added :(").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR,"Please fill out all fields correctly !").show();
        }

        clearInputFields();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txtfieldCustomerId.setText(generateCustomerId());
    }

    private boolean isValidCustomerInputDetails(Customer customerEntity) {
        if (customerEntity.getId().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "CustomerId is required !").show();
            return false;
        }
        if (customerEntity.getName().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Name is required.").show();
            return false;
        }
//        if (!customerEntity.getContact().matches("\\d{10}")) {
//            new Alert(Alert.AlertType.ERROR, "Invalid mobile Number. It must be  10 digits.").show();
//            return false;
//        }
        if (customerEntity.getAddress().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Address is required.").show();
            return false;
        }

        return true;
    }

    private void clearInputFields() {
        txtfieldCustomerId.setText(generateCustomerId());
        txtfieldName.setText("");
        txtfieldAddress.setText("");
        txtFieldContactNo.setText("");
    }



    private String generateCustomerId() {
        String base = "C";
        int integer = Integer.parseInt(customerService.generateCustomerId());
        if (integer < 10) {
            base += "00";
        } else if (integer < 100) {
            base += "0";
        }
        return base+(integer + 1);
    }


}

