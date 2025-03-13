package org.example.btap_cuoi_khoa_1.model;

import java.io.Serializable;
import java.time.LocalTime;

public class Alarm implements Serializable {
    private LocalTime time;
    private String message;
    private boolean isActive;

    public Alarm(LocalTime time, String message, boolean isActive) {
        this.time = time;
        this.message = message;
        this.isActive = isActive;
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

    public boolean isActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return time.toString() + " - " + message;
    }
}
