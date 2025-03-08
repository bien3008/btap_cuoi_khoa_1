package org.example.btap_cuoi_khoa_1;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class SignUpSceneController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TextField useNameField;
    @FXML
    private TextField passwordField;
    @FXML
    private ImageView image;

    @FXML
    private Button signUPButton;
    @FXML
    private Button submitButton;

    @FXML
    private TextField confirmPasswordField;

    private static final String fileName = "userdata.txt";
    private static final String sceneFolder = "scenes";

    private boolean checkName(String username){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(username + ",")) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("errol!");
        }
        return false;
    }
    @FXML
    private void saveToFile() {
        String username = useNameField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert("please fill full of information!");
            return;
        }
        if(checkName(username)){
            showAlert("username exited!");
            return;
        }
        if(confirmPassword.equals(password) != true){
            showAlert("confirm password do not match with password!");
            confirmPasswordField.clear();
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write( username + "\n" + password );
            writer.newLine();
            showAlert("register success!");
            switchToLogin();
        } catch (IOException e) {
            showAlert("errol!");
        }
        // Xóa nội dung TextField sau khi lưu
        useNameField.clear();
        passwordField.clear();
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("notify");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
    @FXML
    void initialize() throws IOException {
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'signUpScene.fxml'.";
        assert signUPButton != null : "fx:id=\"signUPButton\" was not injected: check your FXML file 'signUpScene.fxml'.";

    }
}
