package org.example.btap_cuoi_khoa_1.model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Alarm implements Serializable {
    private LocalTime time;
    private String message;
    private boolean isActive;
    private List<String> activeDays = new ArrayList<>();
    private String musicPath;
    private String musicName;

    public Alarm(LocalTime time, String message, boolean isActive, List<String> activeDays, String musicPath, String musicName) {
        this.time = time;
        this.message = message;
        this.isActive = isActive;
        this.activeDays = activeDays;
        this.musicPath = musicPath;
        this.musicName = musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicName() {
        return musicName;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getMessage() {
        return message;
    }

    public LocalTime getTime() {
        return time;
    }

    public List<String> getActiveDays() {
        return activeDays;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActiveDays(List<String> activeDays) {
        this.activeDays = activeDays;
    }

    public String getDays(){
        if (activeDays == null || activeDays.isEmpty()) {
            return "just today!";
        }
        List<String> allDays = List.of("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday");

        if (activeDays.containsAll(allDays)) {
            return "every day";
        }
        return (activeDays == null || activeDays.isEmpty()) ? "" : String.join(", ", activeDays);
    }
    @Override
    public String toString() {
        return time.toString() + " - " + message;
    }
}
