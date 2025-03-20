package org.example.btap_cuoi_khoa_1;
import java.io.*;
import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.btap_cuoi_khoa_1.manager.UserManager;
import utils.Utils;

public class SignUpSceneController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private AnchorPane pane;
    @FXML
    private URL location;
    @FXML
    private TextField useNameField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private ImageView image;

    @FXML
    private Button signUPButton;
    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;
    @FXML
    private PasswordField passwordField;
    private UserManager userManager = UserManager.getInstance();
    @FXML
    private void initialize() {
        pane.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }
    @FXML
    private void saveToFile() {
        String userName = useNameField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();
        if (userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Utils.showAlert("please fill full of information!");
            return;
        }
        if (userManager.checkName(userName,password) != true) {
            useNameField.clear();
            passwordField.clear();
            confirmPasswordField.clear();
            return;
        }
        if(confirmPassword.equals(password) != true){
            Utils.showAlert("confirm password do not match with password!");
            confirmPasswordField.clear();
            return;
        }
        userManager.addUser(userName,password);
        Utils.showAlert("register success!");
        switchToLogin();
    }
    
    @FXML
    private void switchToLogin() {
        try {
            Stage stage = (Stage) submitButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
