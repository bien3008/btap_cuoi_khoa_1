package org.example.btap_cuoi_khoa_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ProHealthApplication extends Application  {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProHealthApplication.class.getResource("loginScene.fxml"));

        stage.setTitle("ProHealthApp");
        Scene scene = new Scene(fxmlLoader.load() );
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }


}