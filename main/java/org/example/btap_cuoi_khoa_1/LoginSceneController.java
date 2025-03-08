package org.example.btap_cuoi_khoa_1;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private TextField passwordField;
    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Text useName;
    @FXML
    private void checkData(){
        String useName = useNameField.getText().trim();
        String password = passwordField.getText().trim();
        if(useName.isEmpty() || password.isEmpty()){
            showAlert("please fill full of information!");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("userdata.txt"))) {
            String line;
            int d = 1;
            while ((line = reader.readLine()) != null) {
                if(d == 1){
                    if(useName.equals(line) != true){
                        showAlert("incorrect useName or password!");
                        useNameField.clear();
                        passwordField.clear();
                        return;
                    }
                }
                if(d == 2){
                    if(password.equals(line) != true){
                        showAlert("incorrect useName or password!");
                        useNameField.clear();
                        passwordField.clear();
                        return;
                    }
                }
                d++;
            }
            switchToMain();
        } catch (IOException e) {
            showAlert("couldn't read file!");
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
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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

    @FXML
    void initialize() {
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'loginScene.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'loginScene.fxml'.";
        assert signInButton != null : "fx:id=\"signInButton\" was not injected: check your FXML file 'loginScene.fxml'.";
        assert signUpButton != null : "fx:id=\"signUpButton\" was not injected: check your FXML file 'loginScene.fxml'.";
        assert useName != null : "fx:id=\"useName\" was not injected: check your FXML file 'loginScene.fxml'.";

    }

}


