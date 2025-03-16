package org.example.btap_cuoi_khoa_1;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.btap_cuoi_khoa_1.manager.UserManager;
import utils.Utils;

public class LoginSceneController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView image;

    @FXML
    private Text password;
    @FXML
    private TextField useNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Text useName;
    private UserManager userManager = UserManager.getInstance();
    private String currentUser;
    @FXML
    public void initialize() {
        userManager.loadUsers();
        rootPane.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
        Scene scene = rootPane.getScene();
        if (scene != null) {
            rootPane.prefWidthProperty().bind(scene.widthProperty());
            rootPane.prefHeightProperty().bind(scene.heightProperty());
            image.fitWidthProperty().bind(rootPane.widthProperty());
            image.fitHeightProperty().bind(rootPane.heightProperty());
        }
    }
    @FXML
    private void checkData(){
        String useName = useNameField.getText().trim();
        String password = passwordField.getText().trim();
        if(useName.isEmpty() || password.isEmpty()){
            Utils.showAlert("please fill full of information!");
            return;
        }
        if(userManager.logIn(useName,password)){
//            switchToMain();
            switchToTest();
        }
        else {
            Utils.showAlert("incorrect useName or password!");
            useNameField.clear();
            passwordField.clear();
            return;
        }
    }
    @FXML
    private  void switchToTest(){
        try {
            Stage stage = (Stage) signInButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private  void switchToMain(){
        try {
            Stage stage = (Stage) signInButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToSignup() {
        try {
            Stage stage = (Stage) signUpButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("signUpScene.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


