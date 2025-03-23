package org.example.btap_cuoi_khoa_1.view;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import org.example.btap_cuoi_khoa_1.model.Alarm;
import utils.Utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class AlarmNotifications {
    private ObservableList<Alarm> alarmList;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public AlarmNotifications(ObservableList<Alarm> alarmList) {
        this.alarmList = alarmList;
    }

    private boolean checkDay(Alarm alarm){
        List<String> activeDays = alarm.getActiveDays();
        LocalDate today = LocalDate.now();
        DayOfWeek todayOfWeek = today.getDayOfWeek();
        return  activeDays.isEmpty() || alarm.getActiveDays().contains(todayOfWeek.toString().toLowerCase());
    }
    public void startChecking() {
        AlarmSound alarmSound = new AlarmSound();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        scheduler.scheduleAtFixedRate(() -> {
            LocalTime now = LocalTime.now();
            for (Alarm alarm : alarmList) {
                if(alarm.isActive() && checkDay(alarm)) {
                    if (alarm.getTime().toString().equals(now.format(formatter))) {
                        Platform.runLater(() -> Utils.showAlert(alarm.getMessage()));
                        alarmSound.playAlarmSound(alarm.getMusicPath());
//                        alarm.setActive(false);
                    }
                }
            }
        }, 0, 60, TimeUnit.SECONDS);
    }
}
