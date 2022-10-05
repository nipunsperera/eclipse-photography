package lk.eclipse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class RegisterFormController {
    public JFXButton btnRegister;
    public JFXButton btnBack;
    public ComboBox cmbRole;

    public void initialize(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eclipse_db", "root", "Nipun@96");
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Roles WHERE role");
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to connect the database").showAndWait();
            return;
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
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
