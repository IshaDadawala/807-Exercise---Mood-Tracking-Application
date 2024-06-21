package java.com.fitnesstracker.controllers;

import java.com.fitnesstracker.model.User;
import java.com.fitnesstracker.services.GoalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/goal/set-goal")
public class GoalController extends HttpServlet {

    private GoalService goalService;

    @Override
    public void init() throws ServletException {
        goalService = new GoalService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            String goalType = request.getParameter("goalType");
            double goalValue = Double.parseDouble(request.getParameter("goalValue"));

            goalService.setGoal(user, goalType, goalValue);

            response.sendRedirect("dashboard.jsp");
        } else {
            response.sendRedirect("login");
        }
    }
}