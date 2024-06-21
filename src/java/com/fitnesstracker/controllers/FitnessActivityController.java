package java.com.fitnesstracker.controllers;

import java.com.fitnesstracker.model.FitnessActivity;
import java.com.fitnesstracker.model.User;
import java.com.fitnesstracker.services.FitnessActivityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/fitness/log-activity", "/fitness/set-goal"})
public class FitnessActivityController extends HttpServlet {

    private FitnessActivityService fitnessActivityService;

    @Override
    public void init() throws ServletException {
        fitnessActivityService = new FitnessActivityService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/fitness/set-goal":
                showSetGoalPage(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/fitness/log-activity":
                logFitnessActivity(request, response);
                break;
            case "/fitness/set-goal":
                setFitnessGoal(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void logFitnessActivity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            String activityType = request.getParameter("activityType");
            int duration = Integer.parseInt(request.getParameter("duration"));
            double distance = Double.parseDouble(request.getParameter("distance"));
            String intensity = request.getParameter("intensity");
            int caloriesBurned = Integer.parseInt(request.getParameter("caloriesBurned"));

            FitnessActivity activity = new FitnessActivity(activityType, duration, distance, intensity, caloriesBurned);
            fitnessActivityService.logActivity(user, activity);

            response.sendRedirect("fitness-dashboard.jsp");
        } else {
            response.sendRedirect("login");
        }
    }

    private void showSetGoalPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            request.getRequestDispatcher("views/fitness/set-goal.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    private void setFitnessGoal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            String goalType = request.getParameter("goalType");
            double goalValue = Double.parseDouble(request.getParameter("goalValue"));

            fitnessActivityService.setGoal(user, goalType, goalValue);

            response.sendRedirect("fitness-dashboard.jsp");
        } else {
            response.sendRedirect("login");
        }
    }
}