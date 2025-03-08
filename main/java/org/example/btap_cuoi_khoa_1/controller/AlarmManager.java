package org.example.btap_cuoi_khoa_1.controller;

import javafx.fxml.FXML;
import org.example.btap_cuoi_khoa_1.model.Alarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlarmManager {
    public static Scanner sc = new Scanner(System.in);
    public static List<Alarm> alarms = new ArrayList<>();
    @FXML
    public void addAlarm(Alarm alarm){
        alarms.add(alarm);
    }
    public void editAlarm(Alarm alarm){
        String time;
        System.out.println("enter time:");
        time = sc.nextLine();
        alarm.setTime(time);
//        alarm.setActive();
    }
    public void addReminder(){
        
    }
    public void deleteAlarm(Alarm alarm){

//        alarms.remove()
    }
}
