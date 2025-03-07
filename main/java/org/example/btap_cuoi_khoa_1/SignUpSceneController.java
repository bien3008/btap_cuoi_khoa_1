package org.example.btap_cuoi_khoa_1;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class SignUpSceneController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView image;

    @FXML
    private Button signUPButton;

    @FXML
    void initialize() throws IOException {
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'signUpScene.fxml'.";
        assert signUPButton != null : "fx:id=\"signUPButton\" was not injected: check your FXML file 'signUpScene.fxml'.";
//        Stage stage = (Stage) ProHealthApplication.getPrimaryStage();
//
//        // Tải giao diệ n mới
//        Parent root = FXMLLoader.load(getClass().getResource("signUpScene.fxml"));
//
//        // Thay đổi Scene
//        stage.setScene(new Scene(root));
//        stage.show();
        Stage stage = (Stage) signUPButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("signUpScene.fxml"));
        stage.setScene(new Scene(root, 900, 600));
    }
}
