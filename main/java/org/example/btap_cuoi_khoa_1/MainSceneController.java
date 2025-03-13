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
import javafx.scene.layout.VBox;
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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainSceneController {
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

    private boolean isSelectingAdd = false;
    private boolean isSelectingEdit = false;

    @FXML
    private TextArea textArea;
    @FXML
    private ComboBox<LocalTime> timeComboBox;

    AlarmsManager manager = AlarmsManager.getInstance();
    ObservableList<Alarm> alarmList = manager.getAlarmList();
    private final Reminder reminder = new Reminder();
    private AlarmNotifications notifications = new AlarmNotifications(alarmList);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void initialize() {
        manager.loadAlarms();
        alarmListView.setItems(alarmList);
        vBox.setVisible(false);
        alarmListView.setCellFactory(param -> new AlarmCell());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        List<LocalTime> timeOptions = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++) {
            for (int minute = 0; minute < 60; minute += 1) {
                timeOptions.add(LocalTime.of(hour, minute));
            }
        }
        timeComboBox.setItems(FXCollections.observableArrayList(timeOptions));
        timeComboBox.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(LocalTime time) {
                return (time == null) ? "" : time.format(formatter);
            }

            @Override
            public LocalTime fromString(String string) {
                return (string == null || string.isEmpty()) ? null : LocalTime.parse(string, formatter);
            }
        });
        notifications.startChecking();
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
            LocalTime time = timeComboBox.getValue();
            String message = textArea.getText();
            if(time == null && message.isEmpty()){
                vBox.setVisible(false);
                isSelectingAdd = false;
                return;
            }
            if(time == null){
                Utils.showAlert("please choose time for alarm!");
                return;
            }
            if(message.isEmpty()){
                Utils.showAlert("please enter message");
                return;
            }
            Alarm newAlarm = new Alarm(time, message,true);
            alarmList.add(newAlarm);
            vBox.setVisible(false);
            isSelectingAdd = false;
            textArea.clear();
            timeComboBox.setValue(null);
            manager.saveAlarm();
        }

    }
    @FXML
    private void LogOut(){
            switchToLogin();
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

                LocalTime time = timeComboBox.getValue();
                String message = textArea.getText();
                if(alarm == null && (time != null || !message.isEmpty())){
                    Utils.showAlert("please choose an alarm!");
                    return;
                }
                if(time == null && message.isEmpty()){
                    vBox.setVisible(false);
                    isSelectingEdit = false;
                    return;
                }
                if(time != null) {
                    alarm.setTime(time);
                    timeComboBox.setValue(null);
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
