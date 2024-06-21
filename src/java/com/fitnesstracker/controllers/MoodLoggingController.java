package java.com.fitnesstracker.controllers;

import java.com.fitnesstracker.model.Mood;
import java.com.fitnesstracker.services.MoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/mood/*")
public class MoodLoggingController extends HttpServlet {

    private MoodService moodService;

    @Override
    public void init() throws ServletException {
        super.init();
        moodService = new MoodService(); // Initialize your service
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        switch (action) {
            case "/log":
                showMoodLoggingForm(request, response);
                break;
            case "/history":
                showMoodHistory(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if ("/log".equals(action)) {
            logMood(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showMoodLoggingForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/mood/log-mood.jsp").forward(request, response);
    }

    private void logMood(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String moodValue = request.getParameter("mood");
        String notes = request.getParameter("notes");
        String username = (String) request.getSession().getAttribute("username");

        Mood mood = new Mood();
        mood.setMood(moodValue);
        mood.setNotes(notes);

        moodService.logMood(mood, username);
        response.sendRedirect(request.getContextPath() + "/mood/history");
    }

    private void showMoodHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        List<Mood> moodHistory = moodService.getMoodHistory(username);
        request.setAttribute("moodHistory", moodHistory);
        request.getRequestDispatcher("/WEB-INF/views/mood/mood-history.jsp").forward(request, response);
    }
}