package java.com.fitnesstracker.controllers;

import java.com.fitnesstracker.model.Habit;
import java.com.fitnesstracker.model.User;
import java.com.fitnesstracker.services.HabitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = {"/habit/manage-habits", "/habit/track-habits"})
public class HabitController extends HttpServlet {

    private HabitService habitService;

    @Override
    public void init() throws ServletException {
        habitService = new HabitService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/habit/manage-habits":
                showManageHabitsPage(request, response);
                break;
            case "/habit/track-habits":
                showTrackHabitsPage(request, response);
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
            case "/habit/manage-habits":
                manageHabits(request, response);
                break;
            case "/habit/track-habits":
                trackHabits(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void showManageHabitsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            request.setAttribute("habits", habitService.getUserHabits(user));
            request.getRequestDispatcher("views/habit/manage-habits.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    private void manageHabits(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            String action = request.getParameter("action");
            if (action.equals("add")) {
                String habitName = request.getParameter("habitName");
                String frequency = request.getParameter("frequency");
                habitService.addHabit(user, new Habit(habitName, frequency));
            } else if (action.equals("remove")) {
                int habitId = Integer.parseInt(request.getParameter("habitId"));
                habitService.removeHabit(user, habitId);
            }
            response.sendRedirect("manage-habits");
        } else {
            response.sendRedirect("login");
        }
    }

    private void showTrackHabitsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            request.setAttribute("habits", habitService.getUserHabits(user));
            request.setAttribute("date", LocalDate.now()); // Set the current date
            request.getRequestDispatcher("views/habit/track-habits.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    private void trackHabits(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            LocalDate date = LocalDate.parse(request.getParameter("date"));
            String[] completedHabitIds = request.getParameterValues("completedHabits");
            habitService.trackHabits(user, date, completedHabitIds);
            response.sendRedirect("track-habits");
        } else {
            response.sendRedirect("login");
        }
    }
}