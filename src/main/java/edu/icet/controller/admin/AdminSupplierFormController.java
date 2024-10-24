package edu.icet.controller.admin;


import edu.icet.model.Supplier;
import edu.icet.service.Servicefactory;
import edu.icet.service.custom.SupplierService;
import edu.icet.util.ServiceType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminSupplierFormController implements Initializable {

    @FXML
    private TableView<Supplier> tblView;

    @FXML
    private TextField txtFieldItem;

    @FXML
    private TableColumn<?, ?> txtSupplierCompany;

    @FXML
    private TableColumn<?, ?> txtSupplierEmailAddress;

    @FXML
    private TableColumn<?, ?> txtSupplierId;

    @FXML
    private TableColumn<?, ?> txtSupplierItem;

    @FXML
    private TableColumn<?, ?> txtSupplierName;

    @FXML
    private TextField txtfieldCompany;

    @FXML
    private TextField txtfieldEmainAddress;

    @FXML
    private TextField txtfieldSupplierId;

    @FXML
    private TextField txtfieldName;

    @FXML
    private TextField txtfieldSearchSupplier;

    SupplierService supplierService = Servicefactory.getInstance().getServiceType(ServiceType.Supplier);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/add_supplier_form.fxml"))));
            stage.setTitle("Add Supplier");
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (supplierService.delete(txtfieldSupplierId.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier remove successful !").show();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }
        loadTable();
        clearInputFields();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Supplier supplier = new Supplier(
                txtfieldSupplierId.getText(),
                txtfieldName.getText(),
                txtfieldName.getText(),
                txtfieldEmainAddress.getText(),
                txtFieldItem.getText()
        );

        if (isValidSupplierInputDetails(supplier)) {
            if (supplierService.update(supplier)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated successful !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier not updated :(").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please fill out all fields correctly !").show();
        }
        loadTable();
        clearInputFields();
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

    @FXML
    void txtSearchSupplierOnAction(ActionEvent event) {
        setTextToValue(supplierService.search(txtfieldSearchSupplier.getText()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
        txtSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        txtSupplierCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        txtSupplierEmailAddress.setCellValueFactory(new PropertyValueFactory<>("emailaddress"));
        txtSupplierItem.setCellValueFactory(new PropertyValueFactory<>("item"));

        tblView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValue(newValue);
            }
        });

        loadTable();
    }

    private void clearInputFields() {
        txtfieldSupplierId.setText(generateSupplierId());
        txtfieldName.setText("");
        txtfieldCompany.setText("");
        txtfieldEmainAddress.setText("");
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

    private void setTextToValue(Supplier newValue) {
        txtfieldSupplierId.setText(newValue.getId());
        txtfieldName.setText(newValue.getName());
        txtfieldCompany.setText(newValue.getCompany());
        txtfieldEmainAddress.setText(newValue.getEmailaddress());
        txtFieldItem.setText(newValue.getItem());
    }

    private void loadTable() {
        ObservableList<Supplier> supplierObservableList = supplierService.getAllSupplier();
        tblView.setItems(supplierObservableList);
    }
}

