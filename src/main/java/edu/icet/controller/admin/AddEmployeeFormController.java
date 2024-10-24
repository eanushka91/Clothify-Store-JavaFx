package edu.icet.controller.admin;

import edu.icet.model.Employee;
import edu.icet.service.Servicefactory;
import edu.icet.service.custom.EmployeeService;
import edu.icet.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeFormController implements Initializable {

    @FXML
    private TextField txtfieldEmpId;

    @FXML
    private TextField txtfieldAddress;

    @FXML
    private TextField txtfieldCompany;

    @FXML
    private TextField txtfieldContactNo;

    @FXML
    private TextField txtfieldName;

    EmployeeService employeeService = Servicefactory.getInstance().getServiceType(ServiceType.Employee);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Employee employee = new Employee(
                txtfieldEmpId.getText(),
                txtfieldName.getText(),
                txtfieldCompany.getText(),
                txtfieldContactNo.getText(),
                txtfieldAddress.getText()
        );

        if (isValidCustomerInputDetails(employee)) {
            if (employeeService.add(employee)) {
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
        txtfieldEmpId.setText(generateCustomerId());
    }

    private boolean isValidCustomerInputDetails(Employee employeeEntity) {
        if (employeeEntity.getId().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "CustomerId is required !").show();
            return false;
        }
        if (employeeEntity.getName().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Name is required.").show();
            return false;
        }
//        if (!employeeEntity.getContact().matches("\\d{10}")) {
//            new Alert(Alert.AlertType.ERROR, "Invalid mobile Number. It must be  10 digits.").show();
//            return false;
//        }
        if (employeeEntity.getAddress().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Address is required.").show();
            return false;
        }

        return true;
    }

    private void clearInputFields() {
        txtfieldEmpId.setText(generateCustomerId());
        txtfieldName.setText("");
        txtfieldContactNo.setText("");
        txtfieldCompany.setText("");
        txtfieldAddress.setText("");

    }



    private String generateCustomerId() {
        String base = "E";
        int integer = Integer.parseInt(employeeService.generateEmployeeId());
        if (integer < 10) {
            base += "00";
        } else if (integer < 100) {
            base += "0";
        }
        return base+(integer + 1);
    }

}
