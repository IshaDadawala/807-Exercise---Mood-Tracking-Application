package java.com.fitnesstracker.model;

import java.sql.Timestamp;
import java.time.LocalDate;
// import java.time.LocalDateTime;

public class FitnessActivity {
    
    private int id;
    private int userId;
    private String activityType;
    private int duration;
    private double distance;
    private String intensity;
    private int caloriesBurned;
    private LocalDate activityDate;

    public FitnessActivity(String activityType, int duration, double distance, String intensity, int caloriesBurned) {
        this.activityType = activityType;
        this.duration = duration;
        this.distance = distance;
        this.intensity = intensity;
        this.caloriesBurned = caloriesBurned;
        this.activityDate = LocalDate.now();
    }

    // Getters and setters

    public FitnessActivity() {
        //TODO Auto-generated constructor stub
    }

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

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public LocalDate getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }

    public void setActivityId(int int1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setActivityId'");
    }

    public int getActivityId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActivityId'");
    }

    public void setActivityDate(Timestamp timestamp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setActivityDate'");
    }
}
