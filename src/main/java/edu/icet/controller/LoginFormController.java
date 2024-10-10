package edu.icet.controller;

import edu.icet.db.DBConnection;
import edu.icet.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private Text txtForgotPassword;

    @FXML
    private TextField txtfieldEmail;

    @FXML
    private PasswordField txtfieldPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        //Login
        String SQl = "SELECT * FROM user WHERE email=?";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQl);
            psTm.setString(1, txtfieldEmail.getText());

            ResultSet rs = psTm.executeQuery();

            User user = null;
            while (rs.next()) {
                user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }

            if(user != null && txtfieldEmail.getText().equals(user.getEmail()) && txtfieldPassword.getText().equals(user.getPassword()) && user.getRole().equals("admin")){
                Stage stage = new Stage();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/admin_main_form.fxml"))));
                    stage.setTitle("Dashboard");
                    stage.show();
                    txtfieldEmail.getScene().getWindow().hide();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }else if(user != null && txtfieldEmail.getText().equals(user.getEmail()) && txtfieldPassword.getText().equals(user.getPassword()) && user.getRole().equals("employee")){
                Stage stage = new Stage();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/employee/employee_main_form.fxml"))));
                    stage.setTitle("Dashboard");
                    stage.show();
                    txtfieldEmail.getScene().getWindow().hide();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Login Error").showAndWait();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }




    }

}
