package java.com.fitnesstracker.services;

import java.com.fitnesstracker.dao.MoodDAO;
import java.com.fitnesstracker.model.Mood;
import java.util.List;

public class MoodService {
    private MoodDAO moodDAO;

    public MoodService(MoodDAO moodDAO) {
        this.moodDAO = moodDAO;
    }

    public List<Mood> getAllMoodsByUser(int userId) {
        return moodDAO.getAllMoodsByUser(userId);
    }

    public void logMood(Mood mood) {
        //mood.setUsername(username);
        moodDAO.logMood(mood);
    }

    public List<Mood> getMoodHistory(String username) {
        return moodDAO.findByUsername(username);
    }

    public void updateMood(Mood mood) {
        moodDAO.updateMood(mood);
    }

    public void deleteMood(int moodId) {
        moodDAO.deleteMood(moodId);
    }    
}
