package edu.icet.controller.common;

import edu.icet.model.Inventory;
import edu.icet.model.Supplier;
import edu.icet.service.Servicefactory;
import edu.icet.service.custom.InventoryService;
import edu.icet.service.custom.SupplierService;
import edu.icet.util.ServiceType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryFormController implements Initializable {

    @FXML
    private ComboBox<?> cmbProductCategory;

    @FXML
    private TableView<Inventory> tblView;

    @FXML
    private TableColumn<?, ?> txtProductCategory;

    @FXML
    private TableColumn<?, ?> txtProductId;

    @FXML
    private TableColumn<?, ?> txtProductName;

    @FXML
    private TableColumn<?, ?> txtProductPrice;

    @FXML
    private TableColumn<?, ?> txtProductQuantity;

    @FXML
    private TableColumn<?, ?> txtProductSize;

    @FXML
    private TextField txtfieldCategory;

    @FXML
    private TextField txtfieldPrice;

    @FXML
    private TextField txtfieldProductId;

    @FXML
    private TextField txtfieldProductName;

    @FXML
    private TextField txtfieldQuantity;

    @FXML
    private TextField txtfieldSearchProduct;

    @FXML
    private TextField txtfieldSize;

    InventoryService inventoryService = Servicefactory.getInstance().getServiceType(ServiceType.Inventory);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/common/add_inventory_form.fxml"))));
            stage.setTitle("Add Inventory");
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void cmbProductCategoryOnAction(ActionEvent event) {

    }

    @FXML
    void txtProductSearchOnAction(ActionEvent event) {
        setTextToValue(inventoryService.search(txtfieldSearchProduct.getText()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        txtProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        txtProductCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        txtProductSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        txtProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        txtProductQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tblView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValue(newValue);
            }
        });

        loadTable();
    }

    private void clearInputFields() {
        txtfieldProductId.setText(generateSupplierId());
        txtfieldProductName.setText("");
        txtfieldCategory.setText("");
        txtfieldSize.setText("");
        txtfieldPrice.setText("");
        txtfieldQuantity.setText("");
    }

    private String generateSupplierId() {
        String base = "S";
        int integer = Integer.parseInt(inventoryService.generateProductId());
        if (integer < 10) {
            base += "00";
        } else if (integer < 100) {
            base += "0";
        }
        return base+(integer + 1);
    }

    private void setTextToValue(Inventory newValue) {
        txtfieldProductId.setText(newValue.getId());
        txtfieldProductName.setText(newValue.getName());
        txtfieldCategory.setText(newValue.getCategory());
        txtfieldSize.setText(newValue.getSize());
        txtfieldPrice.setText(newValue.getPrice());
        txtfieldQuantity.setText(newValue.getQuantity());
    }

    private void loadTable() {
        ObservableList<Inventory> inventoryObservableList = inventoryService.getAllProduct();
        tblView.setItems(inventoryObservableList);
    }
}


