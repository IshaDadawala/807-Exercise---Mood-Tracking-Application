package java.com.fitnesstracker.services;

import java.com.fitnesstracker.dao.UserDAO;
import java.com.fitnesstracker.model.User;
import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void registerUser(User user) throws SQLException {
        String encryptedPassword = encryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);
        userDAO.createUser(user);
    }

    public User authenticateUser(String username, String password) throws SQLException {
        User user = userDAO.getUserByUsername(username);
        if (user != null && user.getPassword().equals(encryptPassword(password))) {
            return user;
        }
        return null;
    }

    public void updateUserProfile(User user) throws SQLException {
        userDAO.updateUser(user);
    }

    private String encryptPassword(String password) {
        // Implement password encryption logic
        return password;
    }

    public String getMentalHealthAdvice(String username) throws SQLException {
        User user = userDAO.getUserByUsername(username);
        return user.getMentalHealthAdvice();
    }

    public void updateMentalHealthAdvice(String username, String advice) throws SQLException {
        User user = userDAO.getUserByUsername(username);
        user.setMentalHealthAdvice(advice);
        userDAO.updateUser(user);
    }
}