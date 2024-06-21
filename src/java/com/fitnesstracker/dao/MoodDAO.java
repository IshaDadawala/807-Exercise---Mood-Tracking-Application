package java.com.fitnesstracker.dao;

import java.com.fitnesstracker.model.Mood;

import java.com.fitnesstracker.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoodDAO {

    public void create(Mood mood) {
        String sql = "INSERT INTO moods (username, mood, notes, date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mood.getUsername());
            pstmt.setString(2, mood.getMood());
            pstmt.setString(3, mood.getNotes());
            pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Mood> findByUsername(String username) {
        List<Mood> moodHistory = new ArrayList<>();
        String sql = "SELECT * FROM moods WHERE username = ? ORDER BY date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Mood mood = new Mood();
                    mood.setId(rs.getInt("id"));
                    mood.setUsername(rs.getString("username"));
                    mood.setMood(rs.getString("mood"));
                    mood.setNotes(rs.getString("notes"));
                    mood.setMoodDate(rs.getTimestamp("date"));
                    moodHistory.add(mood);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return moodHistory;
    }

    public Mood findById(int id) {
        String sql = "SELECT * FROM moods WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Mood mood = new Mood();
                    mood.setId(rs.getInt("id"));
                    mood.setUsername(rs.getString("username"));
                    mood.setMood(rs.getString("mood"));
                    mood.setNotes(rs.getString("notes"));
                    mood.setMoodDate(rs.getTimestamp("date"));
                    return mood;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Mood mood) {
        String sql = "UPDATE moods SET mood = ?, notes = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mood.getMood());
            pstmt.setString(2, mood.getNotes());
            pstmt.setInt(3, mood.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM moods WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Mood> getAllMoodsByUser(int userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllMoodsByUser'");
    }

    public void logMood(Mood mood) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logMood'");
    }

    public void updateMood(Mood mood) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMood'");
    }

    public void deleteMood(int moodId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMood'");
    }
}