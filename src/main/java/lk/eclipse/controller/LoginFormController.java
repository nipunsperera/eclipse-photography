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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.eclipse.exception.BlankFieldException;
import lk.eclipse.exception.InvalidFieldException;
import lk.eclipse.exception.InvalidRoleException;
import lk.eclipse.security.SecurityContextHolder;
import lk.eclipse.security.User;
import lk.eclipse.security.UserRole;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;

public class LoginFormController {
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public JFXButton btnRegister;
    public JFXButton btnLogin;

    public HashMap<String, Node> textFieldMap;

    public void initialize(){


        textFieldMap = new HashMap<>();
        textFieldMap.put("username",txtUsername);
        textFieldMap.put("password",txtPassword);



    }

    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/RegisterForm.fxml")));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Register User");
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
        btnLogin.getScene().getWindow().hide();

    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String password = txtPassword.getText();
        String username = txtUsername.getText();


        try {
            loginRole(username,password);
        } catch (InvalidRoleException e) {
            new Alert(Alert.AlertType.ERROR,String.format(e.getMessage(),e.getField())).show();
            textFieldMap.get(e.getField()).requestFocus();
            if(textFieldMap.get(e.getField()) instanceof TextField){
                ((TextField) textFieldMap.get(e.getField())).selectAll();
            }

        }

    }

    private void loginRole(String username, String password) throws InvalidRoleException {

        if(username == null ||username.isBlank()){
            throw new BlankFieldException("Please enter the username","username");
        }else if (!username.matches("^[a-z0-9]+$")){
            throw new InvalidFieldException("Invalid username","username");
        }else if (password == null||password.isBlank()) {
            throw new BlankFieldException("Please enter the password","password");
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eclipse_db", "root", "Nipun@96");
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Roles");

            while(rst.next()){
                String usernameFromDb = rst.getString("username");
                String passwordFromDb = rst.getString("password");
                if(username.equals(usernameFromDb) && password.equals(passwordFromDb)){
                    String roleFromDb = rst.getString("role");
                    SecurityContextHolder.setPrincipal(new User(username,UserRole.valueOf(roleFromDb)));
                    Scene scene=null;
                    switch (roleFromDb){
                        case "Admin":
                            scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/AdminForm.fxml")));
                            break;
                        case "Photographer":
                            scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/PhotographerForm.fxml")));
                            break;
                        default:
                            scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/EditorForm.fxml")));
                    }
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Eclipse.LK");
                    stage.setResizable(false);
                    stage.show();
                    stage.centerOnScreen();
                    btnLogin.getScene().getWindow().hide();
                    return;
                }
            }
            new Alert(Alert.AlertType.ERROR,"Invalid Credential. Please try again.").showAndWait();
            txtUsername.requestFocus();
            txtUsername.selectAll();
            return;


        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR,"Unable to connect to the Database").showAndWait();
            return;
        }
    }
}
