package edu.icet.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMainFormController implements Initializable {

    @FXML
    private BorderPane boaderPane;

    @FXML
    private Button btnDashboard;

    private void loadForm(String path){
        try {
            AnchorPane form = FXMLLoader.load(getClass().getResource(path));
            boaderPane.setCenter(form);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        loadForm("/view/common/customer_form.fxml");
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        loadForm("/view/common/dashboard_form.fxml");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        loadForm("/view/admin/admin_employee_form.fxml");
    }

    @FXML
    void btnInventoryOnAction(ActionEvent event) {
        loadForm("/view/common/inventory_form.fxml");
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        Stage stage = new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_form.fxml"))));
            stage.setResizable(false);
            stage.show();
            btnDashboard.getScene().getWindow().hide();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnOrdersOnAction(ActionEvent event) {
        loadForm("/view/common/orders_form.fxml");
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        loadForm("/view/common/placeorder_form.fxml");
    }

    @FXML
    void btnProductOnAction(ActionEvent event) {
        loadForm("/view/common/product_form.fxml");
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        loadForm("/view/admin/admin_supplier_form.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadForm("/view/common/dashboard_form.fxml");
    }
}
