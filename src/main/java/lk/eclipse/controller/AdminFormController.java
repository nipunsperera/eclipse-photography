package lk.eclipse.controller;


import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lk.eclipse.security.SecurityContextHolder;

import java.io.IOException;
import java.net.URL;

public class AdminFormController {

    public Label lblUserName;
    public JFXButton btnAddBookings;
    public JFXButton btnManageRoles;
    public JFXButton btnSettings;
    public JFXButton btnLogout;

    public void initialize(){

        lblUserName.setText(SecurityContextHolder.getPrincipal().getRole()+" : "+SecurityContextHolder.getPrincipal().getUsername());

    }

    public void btnAddBookingsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/BookingManageForm.fxml");
        Parent container = FXMLLoader.load(resource);
        Scene scene = new Scene(container);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        btnLogout.getScene().getWindow().hide();
    }

    public void btnManageRolesOnAction(ActionEvent actionEvent) {
    }

    public void btnSettingsOnAction(ActionEvent actionEvent) {
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        btnLogout.getScene().getWindow().hide();
        Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/LoginForm.fxml")));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();

    }
}


