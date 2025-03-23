package org.example.btap_cuoi_khoa_1;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.btap_cuoi_khoa_1.manager.AlarmsManager;
import org.example.btap_cuoi_khoa_1.model.Alarm;
import org.example.btap_cuoi_khoa_1.view.AlarmCell;
import org.example.btap_cuoi_khoa_1.view.AlarmNotifications;
import org.example.btap_cuoi_khoa_1.view.Reminder;
import utils.Utils;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainSceneController {
    @FXML
    private CheckBox monday;
    @FXML
    private CheckBox tuesday;
    @FXML
    private CheckBox wednesday;
    @FXML
    private CheckBox thursday;
    @FXML
    private CheckBox friday;
    @FXML
    private CheckBox saturday;
    @FXML
    private CheckBox sunday;
    @FXML
    private ComboBox<LocalTime> hourComboBox;
    @FXML
    private ComboBox<LocalTime> minuteComboBox;
    @FXML
    private ListView<Alarm> alarmListView;
    @FXML
    private Button logOutButton;
    @FXML
    private TextField timeField;
    @FXML
    private Button toggleAlarmButton;
    @FXML
    private VBox vBox;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private AnchorPane pane;
    @FXML
    private TextArea textArea;
    @FXML
    private VBox daysContainer;

    private boolean isSelectingAdd = false;
    private boolean isSelectingEdit = false;
    private final Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/f/f1/Ruby_logo_64x64.png");

    AlarmsManager manager = AlarmsManager.getInstance();
    ObservableList<Alarm> alarmList = manager.getAlarmList();
    private final Reminder reminder = new Reminder();
    private AlarmNotifications notifications = new AlarmNotifications(alarmList);
    @FXML
    private List<String> getDays(){
        List<String> days = new ArrayList<>();
        if(monday.isSelected()) days.add("monday");
        if(tuesday.isSelected()) days.add("tuesday");
        if(wednesday.isSelected()) days.add("wednesday");
        if(thursday.isSelected()) days.add("thursday");
        if(friday.isSelected()) days.add("friday");
        if(saturday.isSelected()) days.add("saturday");
        if(sunday.isSelected()) days.add("sunday");
        return days;
    }
    Image backgroundImage = new Image(getClass().getResource("/images/background.jpg").toExternalForm());
    BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
    BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    public void initialize() {
        pane.setBackground(new Background(background));
        manager.loadAlarms();
//        alarmListView.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        hide();
        pane.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        alarmListView.setItems(alarmList);
        alarmListView.setCellFactory(param -> new AlarmCell());
        for (int hour = 0; hour < 24; hour++) {
            hourComboBox.getItems().add(LocalTime.of(hour, 0));
        }

        for (int minute = 0; minute < 60; minute++) {
            minuteComboBox.getItems().add(LocalTime.of(0, minute));
        }
        hourComboBox.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(LocalTime time) {
                return (time == null) ? "" : time.format(DateTimeFormatter.ofPattern("HH"));
            }

            @Override
            public LocalTime fromString(String string) {
                return (string == null || string.isEmpty()) ? null : LocalTime.of(Integer.parseInt(string), 0);
            }
        });

        minuteComboBox.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(LocalTime time) {
                return (time == null) ? "" : time.format(DateTimeFormatter.ofPattern("mm"));
            }

            @Override
            public LocalTime fromString(String string) {
                return (string == null || string.isEmpty()) ? null : LocalTime.of(0, Integer.parseInt(string));
            }
        });

        notifications.startChecking();
        alarmListView.refresh();
//        reminder.startReminder();
    }
    @FXML
    private void showInputFields() {
        vBox.setVisible(true);
        daysContainer.setVisible(true);
    }
    @FXML
    private void hide(){
        vBox.setVisible(false);
        daysContainer.setVisible(false);
        monday.setSelected(false);
        tuesday.setSelected(false);
        wednesday.setSelected(false);
        thursday.setSelected(false);
        friday.setSelected(false);
        saturday.setSelected(false);
        sunday.setSelected(false);

    }
    @FXML
    private void handleRootClick(MouseEvent event) {
        if (!alarmListView.getBoundsInParent().contains(event.getX(), event.getY())) {
            alarmListView.getSelectionModel().clearSelection();
        }

    }

    @FXML
    private void addAlarm() {
        if (isSelectingAdd != true) {
            showInputFields();
            isSelectingAdd = true;
        } else {
            AlarmCell alarmCell = new AlarmCell();
            LocalTime hour = hourComboBox.getValue();
            LocalTime minutes = minuteComboBox.getValue();
            String message = textArea.getText();
            String selectedMusicPath = alarmCell.getSelectedMusicPath();
            if(hour == null && message.isEmpty()){
                hide();
                isSelectingAdd = false;
                return;
            }
            if(hour == null){
                Utils.showAlert("please choose hour for alarm!");
                return;
            }
            if(minutes == null){
                Utils.showAlert("please choose minutes for alarm!");
                return;
            }
            if(message.isEmpty()){
                Utils.showAlert("please enter message");
                return;
            }
            List<String> activeDays = getDays();
            LocalTime time = LocalTime.of(hour.getHour(),minutes.getMinute());
            if (selectedMusicPath == null || selectedMusicPath.isEmpty()) {
                selectedMusicPath = getClass().getResource("/audio/default_sound.mp3").toExternalForm();
            }
//            System.out.println(selectedMusicPath);
            Alarm newAlarm = new Alarm(time, message,true,activeDays,selectedMusicPath,"default sound.mp3");
            alarmList.add(newAlarm);
            hourComboBox.setValue(null);
            minuteComboBox.setValue(null);
            hide();
            isSelectingAdd = false;
            textArea.clear();
            manager.saveAlarm();
        }

    }
    @FXML
    private void LogOut(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("notify");
        alert.setHeaderText("are you want to log out!");
        ButtonType okButton = new ButtonType("ok");
        ButtonType cancelButton = new ButtonType("cancel");
        alert.getButtonTypes().setAll(okButton,cancelButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            reminder.stopReminder();
            switchToLogin();
        }

    }
    @FXML
    private void switchToLogin() {
        try {
            Stage stage = (Stage) logOutButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("loginScene.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void editAlarm(){

        Alarm alarm = alarmListView.getSelectionModel().getSelectedItem();
        if(alarm == null){
            Utils.showAlert("please choose an alarm!");
            return;
        }
        if (isSelectingEdit != true) {
            showInputFields();
            isSelectingEdit = true;
        } else {
            isSelectingEdit = false;
            try {
                LocalTime hour = hourComboBox.getValue();
                LocalTime minutes = minuteComboBox.getValue();
                String message = textArea.getText();
                List<String> activeDays = getDays();
                if(hour == null && minutes == null && message.isEmpty() && activeDays == null){
                    Utils.showAlert("please enter information you want to edit");
                    return;
                }
                if ((hour == null && minutes != null) || (hour != null && minutes == null)) {
                    Utils.showAlert("please choose full of time to edit alarm");
                    return;
                }
                if(hour != null && minutes != null) {
                    hide();
                    LocalTime time = LocalTime.of(hour.getHour(),minutes.getMinute());
                    alarm.setTime(time);
                    hourComboBox.setValue(null);
                    minuteComboBox.setValue(null);

                }
                if(activeDays != null){
                    hide();
                    alarm.setActiveDays(activeDays);

                }
                if(!message.isEmpty()) {
                    alarm.setMessage(message);
                    hide();
                    hourComboBox.setValue(null);
                    minuteComboBox.setValue(null);
                    textArea.clear();
                }
                alarmListView.refresh();
                manager.saveAlarm();
                vBox.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void deleteAlarm(){
        try {
            Alarm alarm = alarmListView.getSelectionModel().getSelectedItem();

            if(alarm == null ){
                Utils.showAlert("please choose an alarm!");
                return;
            }
            alarmList.remove(alarm);
            alarmListView.refresh();
            manager.saveAlarm();
            hide();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
