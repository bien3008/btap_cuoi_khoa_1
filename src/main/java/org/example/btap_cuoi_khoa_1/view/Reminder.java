package org.example.btap_cuoi_khoa_1.view;

import javafx.application.Platform;
import org.example.btap_cuoi_khoa_1.model.Alarm;
import utils.Utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Reminder {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    AlarmSound alarmSound = new AlarmSound();
    public void startReminder() {
        scheduler.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> Utils.showAlert("it's time to stand up and move around, keep fit and get the blood flowing."));
            alarmSound.playAlarmSound( getClass().getResource("/audio/default_sound.mp3").toExternalForm());
        }, 0, 260, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> Utils.showAlert("it's time to drink a cup of water to help your body not to get dehydrated."));
            alarmSound.playAlarmSound( getClass().getResource("/audio/default_sound.mp3").toExternalForm());
        },5 , 260, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> Utils.showAlert("it's time to look into the green space to rest your eyes."));
            alarmSound.playAlarmSound( getClass().getResource("/audio/default_sound.mp3").toExternalForm());
        }, 10, 260, TimeUnit.SECONDS);
    }
    public void stopReminder(){
        alarmSound.stopAlarmSound();
        scheduler.shutdown();
    }
}
