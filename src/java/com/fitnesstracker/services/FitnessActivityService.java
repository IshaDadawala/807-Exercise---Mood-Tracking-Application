package java.com.fitnesstracker.services;

import java.com.fitnesstracker.dao.FitnessActivityDAO;
import java.com.fitnesstracker.model.FitnessActivity;
import java.com.fitnesstracker.model.User;

import java.util.List;

public class FitnessActivityService {

    private FitnessActivityDAO fitnessActivityDAO;

    public FitnessActivityService() {
        fitnessActivityDAO = new FitnessActivityDAO();
    }

    public void logActivity(User user, FitnessActivity activity) {
        fitnessActivityDAO.logActivity(user, activity);
    }

    public List<FitnessActivity> getUserActivities(User user) {
        return fitnessActivityDAO.getUserActivities(user);
    }

    public void setGoal(User user, String goalType, double goalValue) {
        // Implement logic to set and store fitness goals for the user
    }

    // public void updateActivity(FitnessActivity activity) {
    //     fitnessActivityDAO.updateActivity(activity);
    // }

    // public void deleteActivity(int activityId) {
    //     fitnessActivityDAO.deleteActivity(activityId);
    // }
}



























// package java.com.fitnesstracker.services;

// import java.com.fitnesstracker.dao.FitnessActivityDAO;
// import java.com.fitnesstracker.model.FitnessActivity;
// import java.util.List;

// public class FitnessActivityService {
//     private FitnessActivityDAO fitnessActivityDAO;

//     public FitnessActivityService(FitnessActivityDAO fitnessActivityDAO) {
//         this.fitnessActivityDAO = fitnessActivityDAO;
//     }

//     public List<FitnessActivity> getAllActivitiesByUser(int userId) {
//         return fitnessActivityDAO.getAllActivitiesByUser(userId);
//     }

//     public void logActivity(FitnessActivity activity) {
//         fitnessActivityDAO.logActivity(activity);
//     }

    // public void updateActivity(FitnessActivity activity) {
    //     fitnessActivityDAO.updateActivity(activity);
    // }

    // public void deleteActivity(int activityId) {
    //     fitnessActivityDAO.deleteActivity(activityId);
    // }
// }
