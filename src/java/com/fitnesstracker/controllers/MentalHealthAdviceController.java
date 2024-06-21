package java.com.fitnesstracker.controllers;

import java.com.fitnesstracker.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mental-health/*")
public class MentalHealthAdviceController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService(); // Initialize your service
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if ("/advice".equals(action)) {
            showMentalHealthAdvice(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if ("/update-advice".equals(action)) {
            updateMentalHealthAdvice(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showMentalHealthAdvice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        String advice = userService.getMentalHealthAdvice(username);
        request.setAttribute("advice", advice);
        request.getRequestDispatcher("/WEB-INF/views/user/mental-health-advice.jsp").forward(request, response);
    }

    private void updateMentalHealthAdvice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String advice = request.getParameter("advice");
        String username = (String) request.getSession().getAttribute("username");
        userService.updateMentalHealthAdvice(username, advice);
        response.sendRedirect(request.getContextPath() + "/mental-health/advice");
    }
}