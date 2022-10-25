package lk.eclipse.controller;


import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lk.eclipse.security.SecurityContextHolder;

import java.io.IOException;

public class AdminFormController {

    public Label lblUserName;
    public JFXButton btnAddBookings;
    public JFXButton btnManageRoles;
    public JFXButton btnSettings;
    public JFXButton btnLogout;

    public void initialize(){

        lblUserName.setText(SecurityContextHolder.getPrincipal().getRole()+" : "+SecurityContextHolder.getPrincipal().getUsername());

    }

    public void btnAddBookingsOnAction(ActionEvent actionEvent) {
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


