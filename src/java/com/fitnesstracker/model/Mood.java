package java.com.fitnesstracker.model;

import java.sql.Timestamp;
//import java.time.LocalDate;

public class Mood {
    private int id;
    private int userId;
    private String mood;
    private String notes;
    private Timestamp moodDate;

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

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Timestamp getMoodDate() {
        return moodDate;
    }

    public void setMoodDate(Timestamp timestamp) {
        this.moodDate = timestamp;
    }

    public String getUsername() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }

    public void setUsername(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUsername'");
    }
}
