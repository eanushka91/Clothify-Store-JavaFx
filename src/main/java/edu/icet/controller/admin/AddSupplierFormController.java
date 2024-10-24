package edu.icet.controller.admin;

import edu.icet.model.Supplier;
import edu.icet.service.Servicefactory;
import edu.icet.service.custom.SupplierService;
import edu.icet.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddSupplierFormController implements Initializable {

    @FXML
    private TextField txtfieldCompany;

    @FXML
    private TextField txtfieldEmailAddress;

    @FXML
    private TextField txtfieldName;

    @FXML
    private TextField txtFieldItem;

    @FXML
    private TextField txtfieldSupplierId;

    SupplierService supplierService = Servicefactory.getInstance().getServiceType(ServiceType.Supplier);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Supplier supplier = new Supplier(
                txtfieldSupplierId.getText(),
                txtfieldName.getText(),
                txtfieldCompany.getText(),
                txtfieldEmailAddress.getText(),
                txtFieldItem.getText()
        );

        if (isValidSupplierInputDetails(supplier)) {
            if (supplierService.add(supplier)) {
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
        txtfieldSupplierId.setText(generateSupplierId());
    }

    private boolean isValidSupplierInputDetails(Supplier supplier) {
        if (supplier.getId().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Supplier ID required !").show();
            return false;
        }

        if (supplier.getName().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Name is required.").show();
            return false;
        }
        if (!isValidEmail(supplier.getEmailaddress())) {
            new Alert(Alert.AlertType.ERROR, "E-Mail Address is required.").show();
            return false;
        }

        if (supplier.getCompany().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Company is required.").show();
            return false;
        }

        if (supplier.getItem().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Item is required.").show();
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    private void clearInputFields() {
        txtfieldSupplierId.setText(generateSupplierId());
        txtfieldName.setText("");
        txtfieldCompany.setText("");
        txtfieldEmailAddress.setText("");
        txtFieldItem.setText("");
    }


    private String generateSupplierId() {
        String base = "S";
        int integer = Integer.parseInt(supplierService.generateSupplierId());
        if (integer < 10) {
            base += "00";
        } else if (integer < 100) {
            base += "0";
        }
        return base+(integer + 1);
    }
}
