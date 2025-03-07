package org.example.btap_cuoi_khoa_1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Text useName;

    @FXML
    void initialize() {
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'loginScene.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'loginScene.fxml'.";
        assert signInButton != null : "fx:id=\"signInButton\" was not injected: check your FXML file 'loginScene.fxml'.";
        assert signUpButton != null : "fx:id=\"signUpButton\" was not injected: check your FXML file 'loginScene.fxml'.";
        assert useName != null : "fx:id=\"useName\" was not injected: check your FXML file 'loginScene.fxml'.";
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                signUpButton.setText("change");
            }
        });
    }

}


