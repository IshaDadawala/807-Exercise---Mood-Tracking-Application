package java.com.fitnesstracker.services;

import java.com.fitnesstracker.dao.GoalDAO;
import java.com.fitnesstracker.model.Goal;
import java.com.fitnesstracker.model.User;

public class GoalService {

    private GoalDAO goalDAO;

    public GoalService() {
        goalDAO = new GoalDAO();
    }

    public void setGoal(User user, String goalType, double goalValue) {
        Goal goal = new Goal(goalType, goalValue);
        goalDAO.setGoal(user, goal);
    }

    public Goal getGoal(User user) {
        return goalDAO.getGoal(user);
    }
}



// package java.com.fitnesstracker.services;

// import java.com.fitnesstracker.dao.GoalDAO;
// import java.com.fitnesstracker.model.Goal;
// import java.util.List;

// public class GoalService {
//     private GoalDAO goalDAO;

//     public GoalService(GoalDAO goalDAO) {
//         this.goalDAO = goalDAO;
//     }

//     public List<Goal> getAllGoalsByUser(int userId) {
//         return goalDAO.getGoal(userId);
//     }

//     public void setGoal(Goal goal) {
//         goalDAO.setGoal(goal);
//     }

//     public void updateGoal(Goal goal) {
//         goalDAO.updateGoal(goal);
//     }

//     public void deleteGoal(int goalId) {
//         goalDAO.deleteGoal(goalId);
//     }    
// }
