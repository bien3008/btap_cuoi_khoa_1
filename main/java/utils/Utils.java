package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.example.btap_cuoi_khoa_1.view.AlarmSound;

import java.util.Optional;

public class Utils {
    public static void showAlert(String message) {
        AlarmSound alarmSound = new AlarmSound();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("notify");
        alert.setHeaderText(null);
        alert.setContentText(message);
        ButtonType okButton = new ButtonType("ok");
        ButtonType cancelButton = new ButtonType("cancel");
        alert.getButtonTypes().setAll(okButton,cancelButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            alarmSound.stopAlarmSound();
        }
        else{

        }
    }
    public static String fileName = "src/main/Data/userAccount.txt";
}
