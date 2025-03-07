package org.example.btap_cuoi_khoa_1.model;

import org.example.btap_cuoi_khoa_1.controller.Reminder;

import java.util.ArrayList;
import java.util.List;

public class Alarm {
    private String time;
    private boolean isActive;
    private List<Reminder> reminders = new ArrayList<>();
    public Alarm(String time, boolean isActive) {
        this.time = time;
        this.isActive = isActive;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return time;
    }
}
