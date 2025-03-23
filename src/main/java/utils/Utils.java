package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.btap_cuoi_khoa_1.view.AlarmSound;

import java.util.Optional;

public class Utils {
    public static void showAlert(String message) {
        Stage alarmStage = new Stage();
        alarmStage.initStyle(StageStyle.UNDECORATED);
        alarmStage.setAlwaysOnTop(true);
        alarmStage.initModality(Modality.APPLICATION_MODAL);
        AlarmSound alarmSound = new AlarmSound();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("notify");
        alert.setHeaderText("ALARM NOTIFICATION");
        Label contentLabel = new Label(message);
        contentLabel.setWrapText(true);
        contentLabel.setMaxWidth(300);
        alert.getDialogPane().setContent(contentLabel);
        ButtonType okButton = new ButtonType("ok");
        ButtonType cancelButton = new ButtonType("cancel");
        alert.getButtonTypes().setAll(okButton,cancelButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            alarmSound.stopAlarmSound();
        }
    }
    public static String fileName = "src/main/java/org/example/btap_cuoi_khoa_1/model/Data/userAccount.txt";
}
