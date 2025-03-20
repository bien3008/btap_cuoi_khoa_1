package org.example.btap_cuoi_khoa_1.view;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import org.example.btap_cuoi_khoa_1.TestController;
import org.example.btap_cuoi_khoa_1.manager.AlarmsManager;
import org.example.btap_cuoi_khoa_1.manager.UserManager;
import org.example.btap_cuoi_khoa_1.model.Alarm;

import java.io.File;

public class AlarmCell extends ListCell<Alarm> {
    private final Label alarmLabel = new Label();
    private final Button toggleButton = new Button();
    Button chooseMusicButton = new Button("choose alarm sound");
    Label musicLabel = new Label();
    private String selectedMusicPath = "";
    private final HBox hbox = new HBox(30, alarmLabel, toggleButton, chooseMusicButton,musicLabel);
    AlarmsManager manager = AlarmsManager.getInstance();
    public AlarmCell() {
        toggleButton.setOnAction(event -> {
            Alarm alarm = getItem();
            if (alarm != null) {
                alarm.setActive(!alarm.isActive());
                manager.getAlarmList().set(getIndex(), alarm);
                updateItem(alarm, false);
                manager.saveAlarm();
            }
        });
        chooseMusicButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav"));
            File defaultDirectory = new File("C:/btap_cuoi_khoa_1/btap_cuoi_khoa_1/src/main/resources/audio");
            if (defaultDirectory.exists()) {
                fileChooser.setInitialDirectory(defaultDirectory);
            }
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                selectedMusicPath = selectedFile.toURI().toString();
                Alarm alarm = getItem();
                if (alarm != null) {
                    alarm.setMusicPath(selectedMusicPath);
                    alarm.setMusicName(selectedFile.getName());
                    updateItem(alarm, false);
                    manager.getAlarmList().set(getIndex(), alarm);
                    manager.saveAlarm();
                }
            }
        });
    }
    public String getSelectedMusicPath(){
        return selectedMusicPath;
    }
    @Override
    protected void updateItem(Alarm alarm, boolean empty) {
        super.updateItem(alarm, empty);
        if (empty || alarm == null) {
            setGraphic(null);
        } else {
            alarmLabel.setText(alarm.getTime() + " - " + alarm.getMessage() + "\n" + alarm.getDays());
            alarmLabel.setTextFill(Color.WHITE);
            alarmLabel.setFont(new Font("Arial Black",12));
            musicLabel.setText(alarm.getMusicName());
            musicLabel.setTextFill(Color.WHITE);
            musicLabel.setFont(new Font("Arial Black",12));
            Circle circle = new Circle(50);
            toggleButton.setShape(circle);
            toggleButton.setFont(new Font("Arial Black",12));
            chooseMusicButton.setFont(new Font("Arial Black",12));
            chooseMusicButton.setStyle("-fx-background-color:  rgba(255,255,255,0.5);");
            toggleButton.setTextFill(Color.WHITE);
            chooseMusicButton.setTextFill(Color.WHITE);
            if(alarm.isActive()){
                toggleButton.setText("ON");
                toggleButton.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
            }
            else {
                toggleButton.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                toggleButton.setText("OFF");
            }
            setGraphic(hbox);
        }
    }
}
