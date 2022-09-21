package lk.eclipse.controller;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import lk.eclipse.security.SecurityContextHolder;

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

    public void btnLogoutOnAction(ActionEvent actionEvent) {
    }
}


