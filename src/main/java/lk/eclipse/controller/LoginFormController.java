package lk.eclipse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lk.eclipse.exception.BlankFieldException;
import lk.eclipse.exception.InvalidFieldException;
import lk.eclipse.exception.InvalidRoleException;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;

public class LoginFormController {
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public JFXButton btnRegister;
    public JFXButton btnLogin;
    public JFXComboBox cmbRole;
    public HashMap<String, Node> textFieldMap;

    public void initialize(){
        cmbRole.getItems().add("Admin");
        cmbRole.getItems().add("Photographer");
        cmbRole.getItems().add("Editor");
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String password = txtPassword.getText();
        String username = txtUsername.getText();
        String role = cmbRole.selectionModelProperty().getName();

        try {
            loginRole(username,password,role);
        } catch (InvalidRoleException e) {
            new Alert(Alert.AlertType.ERROR,String.format(e.getMessage(),e.getField())).show();

        }

    }

    private void loginRole(String username, String password,String role) throws InvalidRoleException {
        if(username == null ||username.isBlank()){
            throw new BlankFieldException("Please enter the username","username");
        }else if (!username.matches("^[a-z0-9]+$")){
            throw new InvalidFieldException("Invalid username","username");
        }else if (password == null||password.isBlank()) {
            throw new BlankFieldException("Please enter the password","password");
        } else if (role == null ||role.isBlank()) {
            throw new BlankFieldException("Please Select your role","role");
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eclipse_db", "root", "Nipun@96");
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Roles");
            ResultSet rst = stm.executeQuery();
            while(rst.next()){
                String usernameFromDb = rst.getString(2);
                String passwordFromDb = rst.getString(3);
                String roleFromDb = rst.getString(4);

                if(usernameFromDb.equals(username) && passwordFromDb.equals(password) && roleFromDb == role){
                    URL resource = this.getClass().getResource("/view/AdminForm.fxml");
                    Parent container = FXMLLoader.load(resource);
                    Scene scene = new Scene(container);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.setTitle("Admin Form");
                    stage.show();
                    stage.centerOnScreen();

                    btnLogin.getScene().getWindow().hide();

                }else{
                    new Alert(Alert.AlertType.ERROR,"Invalid Credential. Please try again").showAndWait();
                    return;
                }

            }


        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to connect to the Eclipse Database").showAndWait();
            return;
        }
    }
}
