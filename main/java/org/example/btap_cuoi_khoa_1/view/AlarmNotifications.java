package org.example.btap_cuoi_khoa_1.view;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import org.example.btap_cuoi_khoa_1.model.Alarm;
import utils.Utils;

import java.time.LocalTime;
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
        for (Alarm alarm : alarmList) {
            if (alarm.isActive()) {
                Utils.showAlert(alarm.getTime().toString());
//                Platform.runLater(() -> Utils.showAlert(alarm.getMessage()));
            }
        }
//        scheduler.scheduleAtFixedRate(() -> {
//            LocalTime now = LocalTime.now();
//            for (Alarm alarm : alarmList) {
//                if (alarm.isActive()) {
//                    Utils.showAlert(alarm.getTime().toString());
//                    Platform.runLater(() -> Utils.showAlert(alarm.getMessage()));
//                }
//            }
//        }, 0, 1, TimeUnit.SECONDS);
    }


}
