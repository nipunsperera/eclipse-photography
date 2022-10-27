package lk.eclipse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class BookingManagementController {
    public Label lblUserName;
    public JFXButton btnLogout;
    public JFXButton btnBack;
    public JFXButton btnNew;
    public JFXButton btnRemove;
    public JFXButton btnImport;
    public JFXButton btnExport;
    public TableView tableBookings;

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource2 = this.getClass().getResource("/view/AdminForm.fxml");
        Parent container2 = FXMLLoader.load(resource2);
        Scene scene = new Scene(container2);
        Stage stage = new Stage();
        stage.setTitle("Admin Form");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        btnBack.getScene().getWindow().hide();
    }

    public void btnNewOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/AddNewBookingForm.fxml");
        Parent container2 = FXMLLoader.load(resource);
        Scene scene = new Scene(container2);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Admin Form");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
    }

    public void btnImportOnAction(ActionEvent actionEvent) {
    }

    public void btnExportOnAction(ActionEvent actionEvent) {
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/LoginForm.fxml");
        Parent container = FXMLLoader.load(resource);
        Scene scene = new Scene(container);
        Stage stage = new Stage();
        stage.setTitle("Eclipse Photography");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        btnBack.getScene().getWindow().hide();
    }
}
