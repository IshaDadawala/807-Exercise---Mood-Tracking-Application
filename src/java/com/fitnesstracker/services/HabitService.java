package java.com.fitnesstracker.services;

import java.com.fitnesstracker.dao.HabitDAO;
import java.com.fitnesstracker.model.Habit;

import java.util.List;

public class HabitService {
    private HabitDAO habitDAO;

    public HabitService() {
        this.habitDAO = new HabitDAO();
    }

    public void createHabit(Habit habit) {
        habitDAO.create(habit);
    }

    public List<Habit> getHabitsByUsername(String username) {
        return habitDAO.findByUsername(username);
    }

    public Habit getHabitById(int id) {
        return habitDAO.findById(id);
    }

    public void updateHabit(Habit habit) {
        habitDAO.update(habit);
    }

    public void deleteHabit(int id) {
        habitDAO.delete(id);
    }

    public void trackHabit(int habitId, boolean completed) {
        habitDAO.trackHabit(habitId, completed);
    }

    public List<Habit> getHabitTrackingByUsername(String username) {
        return habitDAO.getHabitTrackingByUsername(username);
    }
}