package java.com.fitnesstracker.model;

import java.time.LocalTime;

public class Habit {

    private static int nextId = 1;
    private int id;
    private int userId;
    private String name;
    private String frequency;
    private LocalTime reminder;

    public Habit(String name, String frequency) {
        this.id = nextId++;
        this.name = name;
        this.frequency = frequency;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public LocalTime getReminder() {
        return reminder;
    }

    public void setReminder(LocalTime reminder) {
        this.reminder = reminder;
    }
}
