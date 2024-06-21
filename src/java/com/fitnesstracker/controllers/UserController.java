package java.com.fitnesstracker.controllers;

import java.com.fitnesstracker.dao.UserDAO;
import java.com.fitnesstracker.model.User;
import java.com.fitnesstracker.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/register", "/user/profile", "/user/update-profile"})
public class UserController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        UserDAO userDAO = new UserDAO();
        userService = new UserService(userDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/user/profile":
                showUserProfile(request, response);
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
            case "/login":
                loginUser(request, response);
                break;
            case "/register":
                registerUser(request, response);
                break;
            case "/user/update-profile":
                updateUserProfile(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User authenticatedUser = userService.authenticateUser(username, password);
        if (authenticatedUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", authenticatedUser);
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("views/user/login.jsp").forward(request, response);
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(username, email, password);
        userService.registerUser(user);
        response.sendRedirect("login");
    }

    private void showUserProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            request.setAttribute("user", user);
            request.getRequestDispatcher("views/user/profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    private void updateUserProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));

            user.setName(name);
            user.setAge(age);
            userService.updateUserProfile(user);

            response.sendRedirect("profile");
        } else {
            response.sendRedirect("login");
        }
    }
}