package edu.icet.controller.admin;

import edu.icet.model.Employee;
import edu.icet.service.Servicefactory;
import edu.icet.service.custom.EmployeeService;
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

public class AdminEmployeeFromController implements Initializable {

    @FXML
    private TableView<Employee> tblView;

    @FXML
    private TableColumn<?, ?> txtEmployeeAddress;

    @FXML
    private TableColumn<?, ?> txtEmployeeCompany;

    @FXML
    private TableColumn<?, ?> txtEmployeeContactNo;

    @FXML
    private TableColumn<?, ?> txtEmployeeId;

    @FXML
    private TableColumn<?, ?> txtEmployeeName;

    @FXML
    private TextField txtfieldAddress;

    @FXML
    private TextField txtfieldCompany;

    @FXML
    private TextField txtfieldContactNo;

    @FXML
    private TextField txtfieldEmpId;

    @FXML
    private TextField txtfieldEmpName;

    @FXML
    private TextField txtfieldSearchBar;

    EmployeeService employeeService = Servicefactory.getInstance().getServiceType(ServiceType.Employee);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/add_employee_form.fxml"))));
            stage.setTitle("Add Employee");
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (employeeService.delete(txtfieldEmpId.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Customer remove successful !").show();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }
        loadTable();
        clearInputFields();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Employee employee = new Employee(
                txtfieldEmpId.getText(),
                txtfieldEmpName.getText(),
                txtfieldContactNo.getText(),
                txtfieldCompany.getText(),
                txtfieldAddress.getText()
        );

        if (isValidCustomerInputDetails(employee)) {
            if (employeeService.update(employee)) {
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

    private boolean isValidCustomerInputDetails(Employee employeeEntity) {
        if (employeeEntity.getId().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "CustomerId is required !").show();
            return false;
        }
        if (employeeEntity.getName().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Name is required.").show();
            return false;
        }
        if (!employeeEntity.getContact().matches("\\d{10}")) {
            new Alert(Alert.AlertType.ERROR, "Invalid mobile Number. It must be  10 digits.").show();
            return false;
        }
        if (employeeEntity.getAddress().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Address is required.").show();
            return false;
        }

        return true;
    }

    @FXML
    void txtfieldSearchEmployeeOnAction(ActionEvent event) {
        setTextToValue(employeeService.search(txtfieldSearchBar.getText()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtEmployeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        txtEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        txtEmployeeContactNo.setCellValueFactory(new PropertyValueFactory<>("contact"));
        txtEmployeeCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        txtEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        tblView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValue(newValue);
            }
        });

        loadTable();
    }

    private void setTextToValue(Employee newValue) {
        txtfieldEmpId.setText(newValue.getId());
        txtfieldEmpName.setText(newValue.getName());
        txtfieldContactNo.setText(newValue.getContact());
        txtfieldCompany.setText(newValue.getCompany());
        txtfieldAddress.setText(newValue.getAddress());
    }

    private void clearInputFields() {
        txtfieldEmpId.setText(generateCustomerId());
        txtfieldEmpName.setText("");
        txtfieldAddress.setText("");
        txtfieldCompany.setText("");
        txtfieldContactNo.setText("");
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

    private void loadTable() {
        ObservableList<Employee> employeeObservableList = employeeService.getAllEmployee();
        tblView.setItems(employeeObservableList);
    }
}
