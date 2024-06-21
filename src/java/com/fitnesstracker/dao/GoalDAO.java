// src/java/com/fitnesstracker/dao/GoalDAO.java

package java.com.fitnesstracker.dao;

import java.com.fitnesstracker.model.Goal;
import java.com.fitnesstracker.model.User;
import java.com.fitnesstracker.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoalDAO {

    public void createGoal(Goal goal) throws SQLException {
        String sql = "INSERT INTO goals (user_id, goal_type, target_value, current_value, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, goal.getUserId());
            pstmt.setString(2, goal.getGoalType());
            pstmt.setDouble(3, goal.getTargetValue());
            pstmt.setDouble(4, goal.getCurrentValue());
            pstmt.setDate(5, new java.sql.Date(goal.getStartDate().getTime()));
            pstmt.setDate(6, new java.sql.Date(goal.getEndDate().getTime()));
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating goal failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    goal.setGoalId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating goal failed, no ID obtained.");
                }
            }
        }
    }

    public Goal getGoalById(int goalId) throws SQLException {
        String sql = "SELECT * FROM goals WHERE goal_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, goalId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractGoalFromResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Goal> getGoalsByUserId(int userId) throws SQLException {
        List<Goal> goals = new ArrayList<>();
        String sql = "SELECT * FROM goals WHERE user_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    goals.add(extractGoalFromResultSet(rs));
                }
            }
        }
        return goals;
    }

    public void updateGoal(Goal goal) throws SQLException {
        String sql = "UPDATE goals SET goal_type = ?, target_value = ?, current_value = ?, start_date = ?, end_date = ? WHERE goal_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, goal.getGoalType());
            pstmt.setDouble(2, goal.getTargetValue());
            pstmt.setDouble(3, goal.getCurrentValue());
            pstmt.setDate(4, new java.sql.Date(goal.getStartDate().getTime()));
            pstmt.setDate(5, new java.sql.Date(goal.getEndDate().getTime()));
            pstmt.setInt(6, goal.getGoalId());
            
            pstmt.executeUpdate();
        }
    }

    public void deleteGoal(int goalId) throws SQLException {
        String sql = "DELETE FROM goals WHERE goal_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, goalId);
            pstmt.executeUpdate();
        }
    }

    private Goal extractGoalFromResultSet(ResultSet rs) throws SQLException {
        Goal goal = new Goal();
        goal.setGoalId(rs.getInt("goal_id"));
        goal.setUserId(rs.getInt("user_id"));
        goal.setGoalType(rs.getString("goal_type"));
        goal.setTargetValue(rs.getDouble("target_value"));
        goal.setCurrentValue(rs.getDouble("current_value"));
        goal.setStartDate(rs.getDate("start_date"));
        goal.setEndDate(rs.getDate("end_date"));
        return goal;
    }

    public void setGoal(User user, Goal goal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGoal'");
    }

    public Goal getGoal(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGoal'");
    }
}






































// package java.com.fitnesstracker.dao;

// import java.com.fitnesstracker.model.Goal;
// import java.com.fitnesstracker.model.User;

// import java.util.HashMap;
// import java.util.Map;

// public class GoalDAO {

//     private Map<User, Goal> goals = new HashMap<>();

//     public void setGoal(User user, Goal goal) {
//         goals.put(user, goal);
//     }

//     public Goal getGoal(User user) {
//         return goals.get(user);
//     }

//     public Goal updateGoal(User user) {
//         return goals.get(user);
//     }

//     public Goal deleteGoal(User user) {
//         return goals.get(user);
//     }
// }



// // package java.com.fitnesstracker.dao;

// // import java.com.fitnesstracker.model.Goal;
// // import java.util.List;

// // public interface GoalDAO {
// //     List<Goal> getAllGoalsByUser(int userId);
// //     void setGoal(Goal goal);
// //     void updateGoal(Goal goal);
// //     void deleteGoal(int goalId);
// // }