package org.example.btap_cuoi_khoa_1.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.example.btap_cuoi_khoa_1.model.Alarm;
import utils.Utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestController {
    @FXML
    private ListView<Alarm> alarmListView;
    @FXML
    private TextField timeField;
    @FXML
    private VBox vBox;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;

    private boolean isselectingAdd = false;
    private boolean isSelectingEdit = false;
    
    @FXML
    private TextArea textArea;
    @FXML
    private ComboBox<LocalTime> timeComboBox;
    AlarmsManager manager = AlarmsManager.getInstance();
    ObservableList<Alarm> alarmList = manager.getAlarmList();
    public void initialize() {
        manager.loadAlarms();
        alarmListView.setItems(alarmList);
        vBox.setVisible(false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        List<LocalTime> timeOptions = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++) {
            for (int minute = 0; minute < 60; minute += 5) {
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
        if (isselectingAdd != true) {
            showInputFields();
            isselectingAdd = true;
        } else {
            LocalTime time = timeComboBox.getValue();
            String message = textArea.getText();
            if(time == null && message.isEmpty()){
                vBox.setVisible(false);
                isselectingAdd = false;
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
            isselectingAdd = false;
            textArea.clear();
            timeComboBox.setValue(null);
            manager.saveAlarm();
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
            vBox.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
