package java.com.fitnesstracker.dao;

import java.com.fitnesstracker.model.FitnessActivity;
import java.com.fitnesstracker.model.User;
import java.com.fitnesstracker.util.DBConnection;

import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FitnessActivityDAO {

    public void createFitnessActivity(FitnessActivity activity) throws SQLException {
        String sql = "INSERT INTO fitness_activities (user_id, activity_type, duration, distance, intensity, calories_burned, activity_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, activity.getUserId());
            pstmt.setString(2, activity.getActivityType());
            pstmt.setInt(3, activity.getDuration());
            pstmt.setDouble(4, activity.getDistance());
            pstmt.setString(5, activity.getIntensity());
            pstmt.setInt(6, (int) activity.getCaloriesBurned());
            pstmt.setTimestamp(7, new Timestamp(activity.getActivityDate().getTime()));
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating fitness activity failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    activity.setActivityId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating fitness activity failed, no ID obtained.");
                }
            }
        }
    }

    public FitnessActivity getFitnessActivityById(int activityId) throws SQLException {
        String sql = "SELECT * FROM fitness_activities WHERE activity_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, activityId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractFitnessActivityFromResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<FitnessActivity> getFitnessActivitiesByUserId(int userId) throws SQLException {
        List<FitnessActivity> activities = new ArrayList<>();
        String sql = "SELECT * FROM fitness_activities WHERE user_id = ? ORDER BY activity_date DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    activities.add(extractFitnessActivityFromResultSet(rs));
                }
            }
        }
        return activities;
    }

    public void updateFitnessActivity(FitnessActivity activity) throws SQLException {
        String sql = "UPDATE fitness_activities SET activity_type = ?, duration = ?, distance = ?, intensity = ?, calories_burned = ?, activity_date = ? WHERE activity_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, activity.getActivityType());
            pstmt.setInt(2, activity.getDuration());
            pstmt.setDouble(3, activity.getDistance());
            pstmt.setString(4, activity.getIntensity());
            pstmt.setInt(5, (int) activity.getCaloriesBurned());
            pstmt.setTimestamp(6, new Timestamp(activity.getActivityDate().getTime()));
            pstmt.setInt(7, activity.getActivityId());
            
            pstmt.executeUpdate();
        }
    }

    public void deleteFitnessActivity(int activityId) throws SQLException {
        String sql = "DELETE FROM fitness_activities WHERE activity_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, activityId);
            pstmt.executeUpdate();
        }
    }

    private FitnessActivity extractFitnessActivityFromResultSet(ResultSet rs) throws SQLException {
        FitnessActivity activity = new FitnessActivity();
        activity.setActivityId(rs.getInt("activity_id"));
        activity.setUserId(rs.getInt("user_id"));
        activity.setActivityType(rs.getString("activity_type"));
        activity.setDuration(rs.getInt("duration"));
        activity.setDistance(rs.getDouble("distance"));
        activity.setIntensity(rs.getString("intensity"));
        activity.setCaloriesBurned(rs.getInt("calories_burned"));
        activity.setActivityDate(rs.getTimestamp("activity_date"));
        return activity;
    }

    public List<FitnessActivity> getFitnessActivitiesByUserIdAndDateRange(int userId, Date startDate, Date endDate) throws SQLException {
        List<FitnessActivity> activities = new ArrayList<>();
        String sql = "SELECT * FROM fitness_activities WHERE user_id = ? AND activity_date BETWEEN ? AND ? ORDER BY activity_date DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            pstmt.setTimestamp(2, new Timestamp(startDate.getTime()));
            pstmt.setTimestamp(3, new Timestamp(endDate.getTime()));
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    activities.add(extractFitnessActivityFromResultSet(rs));
                }
            }
        }
        return activities;
    }

    public int getTotalCaloriesBurnedByUserIdAndDateRange(int userId, Date startDate, Date endDate) throws SQLException {
        String sql = "SELECT SUM(calories_burned) as total_calories FROM fitness_activities WHERE user_id = ? AND activity_date BETWEEN ? AND ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            pstmt.setTimestamp(2, new Timestamp(startDate.getTime()));
            pstmt.setTimestamp(3, new Timestamp(endDate.getTime()));
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total_calories");
                }
            }
        }
        return 0;
    }

    public double getTotalDistanceByUserIdAndDateRange(int userId, Date startDate, Date endDate) throws SQLException {
        String sql = "SELECT SUM(distance) as total_distance FROM fitness_activities WHERE user_id = ? AND activity_date BETWEEN ? AND ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            pstmt.setTimestamp(2, new Timestamp(startDate.getTime()));
            pstmt.setTimestamp(3, new Timestamp(endDate.getTime()));
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total_distance");
                }
            }
        }
        return 0.0;
    }

    public int getTotalDurationByUserIdAndDateRange(int userId, Date startDate, Date endDate) throws SQLException {
        String sql = "SELECT SUM(duration) as total_duration FROM fitness_activities WHERE user_id = ? AND activity_date BETWEEN ? AND ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            pstmt.setTimestamp(2, new Timestamp(startDate.getTime()));
            pstmt.setTimestamp(3, new Timestamp(endDate.getTime()));
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total_duration");
                }
            }
        }
        return 0;
    }

    public void logActivity(User user, FitnessActivity activity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logActivity'");
    }

    public List<FitnessActivity> getUserActivities(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserActivities'");
    }
}