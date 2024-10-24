package edu.icet.controller.common;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductFormController {

    @FXML
    private TextField txtfieldName;

    @FXML
    private TextField txtfieldPrice;

    @FXML
    private TextField txtfieldProductId;

    @FXML
    private TextField txtfieldQuantity;

    @FXML
    private TextField txtfieldSearchProduct;

    @FXML
    private TextField txtfieldSize;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/common/add_product_form.fxml"))));
            stage.setTitle("Add Product");
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

}
