package org.example.btap_cuoi_khoa_1.view;

import javafx.application.Platform;
import utils.Utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Reminder {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void startReminder() {
        scheduler.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> Utils.showAlert("it's time to stand up and move around, keep fit and get the blood flowing."));
        }, 5, 5, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> Utils.showAlert("it's time to drink a cup of water to help your body not to get dehydrated."));
        }, 7, 7, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> Utils.showAlert("it's time to look into the green space to rest your eyes."));
        }, 20, 20, TimeUnit.SECONDS);
    }

}
