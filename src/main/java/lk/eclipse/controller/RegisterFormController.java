package lk.eclipse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

public class RegisterFormController {
    public JFXButton btnRegister;
    public JFXButton btnBack;
    public ComboBox cmbRole;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtContact;
    public PasswordField txtPassword;
    public PasswordField txtPassword2;
    public TextField txtUsername;

    public void initialize(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eclipse_db", "root", "Nipun@96");
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Eclipse_roles");

            while(rst.next()){
                String eclipse_role = rst.getString(1);
                cmbRole.getItems().add(eclipse_role);
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to connect the database").showAndWait();
            return;
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        if (!txtFirstName.getText().matches("[A-Za-z]+")) {
            new Alert(Alert.AlertType.ERROR, "Please Enter a valid name").showAndWait();
            txtFirstName.requestFocus();
            return;
        }else if (!txtLastName.getText().matches("[A-Za-z]+")) {
            new Alert(Alert.AlertType.ERROR, "Please Enter a valid name").showAndWait();
            txtFirstName.requestFocus();
            return;

        }else if (!txtContact.getText().matches("^\\d{3}-\\d{7}$")) {
            new Alert(Alert.AlertType.ERROR, "Please Enter a valid contact number").showAndWait();
            txtContact.requestFocus();
            return;

        }else if(!txtUsername.getText().matches("[A-Za-z1-9@_]{6,}")) {
            new Alert(Alert.AlertType.ERROR, "Please Enter a valid username").showAndWait();
            txtUsername.requestFocus();
            return;
        }else if(cmbRole.getValue()==null){
            new Alert(Alert.AlertType.ERROR, "Please Select a role").showAndWait();
            cmbRole.requestFocus();
            return;
        }else if (!txtPassword.getText().matches("[A-Za-z1-9.@_]{6,}")) {
            new Alert(Alert.AlertType.ERROR, "Please Enter a valid password with minimum 6 characters").showAndWait();
            txtPassword.requestFocus();
            return;
        }else if (!txtPassword.getText().equals(txtPassword2.getText())) {
            new Alert(Alert.AlertType.ERROR, "Password is not matched").showAndWait();
            txtPassword2.requestFocus();
            return;
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eclipse_db", "root", "Nipun@96");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Register (f_name,l_name,username,contact,role_user,password1,password2) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1,txtFirstName.getText());
            preparedStatement.setString(2,txtLastName.getText());
            preparedStatement.setString(3,txtUsername.getText());
            preparedStatement.setString(4,txtContact.getText());
            preparedStatement.setString(5,cmbRole.valueProperty().getName());
            preparedStatement.setString(6,txtPassword.getText());
            preparedStatement.setString(7,txtPassword2.getText());
            int i = preparedStatement.executeUpdate();
            System.out.println(i);
            new Alert(Alert.AlertType.INFORMATION,"Registered Successfully !").showAndWait();
            txtFirstName.clear();
            txtLastName.clear();
            txtContact.clear();
            cmbRole.getSelectionModel().clearSelection();
            txtPassword.clear();
            txtPassword2.clear();
            txtUsername.clear();
            txtFirstName.requestFocus();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Cannot connect with the database").showAndWait();
            e.printStackTrace();
            return;
        }


    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/LoginForm.fxml");
        Parent container = FXMLLoader.load(resource);
        Scene scene = new Scene(container);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Eclipse Photography");
        stage.centerOnScreen();
        btnBack.getScene().getWindow().hide();
        stage.show();
    }
}
