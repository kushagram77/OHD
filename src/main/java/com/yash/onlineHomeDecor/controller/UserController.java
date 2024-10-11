/**
 * @author Darshan soni
 * @version 1.0
 */
package com.yash.onlineHomeDecor.controller;

import com.yash.onlineHomeDecor.domain.User;
import com.yash.onlineHomeDecor.service.UserService;
import com.yash.onlineHomeDecor.serviceimpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/user/*")
public class UserController extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/login";
        }

        switch (action) {
            case "/register":
                showRegistrationForm(request, response);
                break;
            case "/login":
                showLoginForm(request, response);
                break;
            case "/profile":
                showUserProfile(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/login";
        }

        switch (action) {
            case "/register":
                registerUser(request, response);
                break;
            case "/login":
                loginUser(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void showRegistrationForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }

    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    private void showUserProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Assuming user is stored in session after login
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return;
        }
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String dobStr = request.getParameter("dateOfBirth");
        String role = request.getParameter("role"); // "customer" or "seller"

        Date dateOfBirth = null;
        try {
            dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dobStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(address);
        user.setDateOfBirth(dateOfBirth);
        user.setRole(role);

        try {
            userService.registerUser(user);
            response.sendRedirect(request.getContextPath() + "/user/login");
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userService.loginUser(email, password);
            request.getSession().setAttribute("currentUser", user);
            response.sendRedirect(request.getContextPath() + "/user/profile");
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}

