package org.example.btap_cuoi_khoa_1;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.btap_cuoi_khoa_1.manager.AlarmsManager;
import org.example.btap_cuoi_khoa_1.model.Alarm;
import org.example.btap_cuoi_khoa_1.view.AlarmCell;
import org.example.btap_cuoi_khoa_1.view.AlarmNotifications;
import org.example.btap_cuoi_khoa_1.view.AlarmSound;
import org.example.btap_cuoi_khoa_1.view.Reminder;
import utils.Utils;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
    private ComboBox<LocalTime> timeComboBox;

    private boolean isSelectingAdd = false;
    private boolean isSelectingEdit = false;

    AlarmsManager manager = AlarmsManager.getInstance();
    ObservableList<Alarm> alarmList = manager.getAlarmList();
    private final Reminder reminder = new Reminder();
    private AlarmNotifications notifications = new AlarmNotifications(alarmList);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

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

    public void initialize() {
        manager.loadAlarms();

        pane.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
        alarmListView.setItems(alarmList);
        vBox.setVisible(false);
        alarmListView.setCellFactory(param -> new AlarmCell());
        List<LocalTime> timeOptions = new ArrayList<>();
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
        reminder.startReminder();
    }
    @FXML
    private void showInputFields() {
        vBox.setVisible(true);
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
            LocalTime hour = hourComboBox.getValue();
            LocalTime minutes = minuteComboBox.getValue();
            List<String> activeDays = getDays();
            String message = textArea.getText();
            if(hour == null && message.isEmpty()){
                vBox.setVisible(false);
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
            LocalTime time = LocalTime.of(hour.getHour(),minutes.getMinute());
            Alarm newAlarm = new Alarm(time, message,true,activeDays);
            alarmList.add(newAlarm);
            hourComboBox.setValue(null);
            minuteComboBox.setValue(null);
            vBox.setVisible(false);
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
            switchToLogin();
            reminder.stopReminder();
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

        if (isSelectingEdit != true) {
            showInputFields();
            isSelectingEdit = true;
        } else {
            isSelectingEdit = false;

            try {
                Alarm alarm = alarmListView.getSelectionModel().getSelectedItem();
                LocalTime hour = hourComboBox.getValue();
                LocalTime minutes = minuteComboBox.getValue();
                String message = textArea.getText();
                if(alarm == null && ((hour != null && minutes != null) || !message.isEmpty())){
                    Utils.showAlert("please choose an alarm!");
                    return;
                }
                if(hour == null && minutes == null && message.isEmpty()){
                    vBox.setVisible(false);
                    isSelectingEdit = false;
                    return;
                }
                if(hour != null && minutes != null) {
                    LocalTime time = LocalTime.of(hour.getHour(),minutes.getMinute());
                    alarm.setTime(time);
                    hourComboBox.setValue(null);
                    minuteComboBox.setValue(null);
                }
                if(!message.isEmpty()) {
                    alarm.setMessage(message);
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
            vBox.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
