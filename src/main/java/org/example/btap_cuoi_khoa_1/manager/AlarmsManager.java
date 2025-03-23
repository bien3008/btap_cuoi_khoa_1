package org.example.btap_cuoi_khoa_1.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.btap_cuoi_khoa_1.model.Alarm;

import java.io.*;
import java.util.ArrayList;

public class AlarmsManager {
    private static AlarmsManager instance;
    private ObservableList<Alarm> alarmList = FXCollections.observableArrayList();
    UserManager manager = UserManager.getInstance();
    private AlarmsManager() {}
    public static AlarmsManager getInstance() {
        if (instance == null) {
            instance = new AlarmsManager();
        }
        return instance;
    }

    public ObservableList<Alarm> getAlarmList() {
        return alarmList;
    }
    public void loadAlarms() {
        String fileName = "src/main/java/org/example/btap_cuoi_khoa_1/model/Data/usersData/" + manager.getCurrentUser() + ".dat";
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            alarmList.setAll((ArrayList<Alarm>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            alarmList.clear();
        }
    }
    public void saveAlarm() {
        String fileName = "src/main/java/org/example/btap_cuoi_khoa_1/model/Data/usersData/" + manager.getCurrentUser() + ".dat";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(new ArrayList<>(alarmList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
