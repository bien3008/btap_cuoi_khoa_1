package org.example.btap_cuoi_khoa_1.view;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import org.example.btap_cuoi_khoa_1.model.Alarm;
import utils.Utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AlarmNotifications {
    private ObservableList<Alarm> alarmList;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public AlarmNotifications(ObservableList<Alarm> alarmList) {
        this.alarmList = alarmList;
    }
    public void startChecking() {
//        for (Alarm alarm : alarmList) {
//            if (alarm.isActive()) {
//                Utils.showAlert(alarm.getTime().toString());
////                Platform.runLater(() -> Utils.showAlert(alarm.getMessage()));
//            }
//        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//        LocalTime time = LocalTime.now();
//        time.format(formatter);
//        Utils.showAlert(time.format(formatter).toString());
        scheduler.scheduleAtFixedRate(() -> {
            LocalTime now = LocalTime.now();
            for (Alarm alarm : alarmList) {
                if (alarm.isActive() && alarm.getTime().toString().equals(now.format(formatter))) {
                    Platform.runLater(() -> Utils.showAlert(alarm.getMessage()));
                }
            }
        }, 0, 1, TimeUnit.MINUTES);
    }


}
