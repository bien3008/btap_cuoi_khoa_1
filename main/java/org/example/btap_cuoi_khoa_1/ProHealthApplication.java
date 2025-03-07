package org.example.btap_cuoi_khoa_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProHealthApplication extends Application  {

    public static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(ProHealthApplication.class.getResource("loginScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load() );
        stage.setScene(scene);
        stage.show();
    }
    public static Object getPrimaryStage() {
        return primaryStage;
    }
    public static void main(String[] args) {
        launch();
    }


}