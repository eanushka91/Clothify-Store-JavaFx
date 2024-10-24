package edu.icet.controller.common;

import edu.icet.model.Customer;
import edu.icet.service.Servicefactory;
import edu.icet.service.custom.CustomerService;
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

public class CustomerFormController implements Initializable {

    @FXML
    private TableView<Customer> tblView;

    @FXML
    private TableColumn<?, ?> txtAddress;

    @FXML
    private TableColumn<?, ?> txtContactNo;

    @FXML
    private TableColumn<?, ?> txtCustomerId;

    @FXML
    private TextField txtFieldContactNo;

    @FXML
    private TableColumn<?, ?> txtName;

    @FXML
    private TextField txtfieldAddress;

    @FXML
    private TextField txtfieldCustomerId;

    @FXML
    private TextField txtfieldName;

    @FXML
    private TextField txtfieldSearchCustomer;

    CustomerService customerService = Servicefactory.getInstance().getServiceType(ServiceType.Customer);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/common/add_customer_form.fxml"))));
            stage.setTitle("Add Customer");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (customerService.deleteCustomer(txtfieldCustomerId.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Customer remove successful !").show();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }
        loadTable();
        clearInputFields();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Customer customer = new Customer(
                txtfieldCustomerId.getText(),
                txtfieldName.getText(),
                txtFieldContactNo.getText(),
                txtfieldAddress.getText()
        );

        if (isValidCustomerInputDetails(customer)) {
            if (customerService.updateCustomer(customer)) {
                new Alert(Alert.AlertType.INFORMATION, "Customer updated successful !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer not updated :(").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please fill out all fields correctly !").show();
        }
        loadTable();
        clearInputFields();

    }

    @FXML
    void txtfieldSearchCustomerOnAction(ActionEvent event) {
        setTextToValue(customerService.searchCustomer(txtfieldSearchCustomer.getText()));
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        txtName.setCellValueFactory(new PropertyValueFactory<>("name"));
        txtContactNo.setCellValueFactory(new PropertyValueFactory<>("contact"));
        txtAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        tblView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValue(newValue);
            }
        });

        loadTable();

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

    private void setTextToValue(Customer newValue) {
        txtfieldCustomerId.setText(newValue.getId());
        txtfieldName.setText(newValue.getName());
        txtFieldContactNo.setText(newValue.getContact());
        txtfieldAddress.setText(newValue.getAddress());
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
            base += "000";
        } else if (integer < 100) {
            base += "00";
        } else if (integer < 1000) {
            base += "0";
        }
        return base+(integer + 1);
    }

    private void loadTable() {
        ObservableList<Customer> customerObservableList = customerService.getAllCustomers();
        tblView.setItems(customerObservableList);
    }
}


