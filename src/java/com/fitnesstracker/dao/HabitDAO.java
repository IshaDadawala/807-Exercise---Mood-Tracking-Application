package java.com.fitnesstracker.dao;

import java.com.fitnesstracker.model.Habit;
import java.com.fitnesstracker.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HabitDAO {

    private Map<User, List<Habit>> userHabits = new HashMap<>();
    private Map<User, Map<LocalDate, List<Integer>>> habitTracker = new HashMap<>();

    public void addHabit(User user, Habit habit) {
        List<Habit> habits = userHabits.get(user);
        if (habits == null) {
            habits = new ArrayList<>();
            userHabits.put(user, habits);
        }
        habits.add(habit);
    }

    public void removeHabit(User user, int habitId) {
        List<Habit> habits = userHabits.get(user);
        if (habits != null) {
            habits.removeIf(habit -> habit.getId() == habitId);
        }
    }

    // public void updateHabit(Habit habit);

    public List<Habit> getUserHabits(User user) {
        return userHabits.get(user);
    }

    public void trackHabits(User user, LocalDate date, List<Integer> completedHabitIds) {
        Map<LocalDate, List<Integer>> userTracker = habitTracker.get(user);
        if (userTracker == null) {
            userTracker = new HashMap<>();
            habitTracker.put(user, userTracker);
        }
        userTracker.put(date, completedHabitIds);
    }

    public Map<LocalDate, List<Integer>> getHabitTracker(User user) {
        return habitTracker.get(user);
    }

    public void create(Habit habit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    public List<Habit> findByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }

    public Habit findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public void update(Habit habit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public void delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    public void trackHabit(int habitId, boolean completed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trackHabit'");
    }

    public List<Habit> getHabitTrackingByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHabitTrackingByUsername'");
    }
}




















// package java.com.fitnesstracker.dao;

// import java.com.fitnesstracker.model.Habit;
// import java.util.List;

// public interface HabitDAO {
//     List<Habit> getAllHabitsByUser(int userId);
//     void addHabit(Habit habit);
//     void updateHabit(Habit habit);
//     void deleteHabit(int habitId);
//     void trackHabits(int userId, List<Integer> completedHabits);
// }

