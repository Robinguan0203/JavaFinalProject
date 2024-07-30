/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers;

import com.fwrp.constants.FileLocationConstant;
import com.fwrp.constants.NotificationMethodConstant;
import com.fwrp.constants.UserTypeConstant;
import com.fwrp.dataaccess.DataSource;
import static com.fwrp.dataaccess.DataSource.openPropsFile;
import com.fwrp.dbService.UserDbService;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.exceptions.DataNotExistsException;
import com.fwrp.models.Charity;
import com.fwrp.models.Consumer;
import com.fwrp.models.Notification;
import com.fwrp.models.Retailer;
import com.fwrp.models.User;
import com.fwrp.services.UserService;
import com.fwrp.validator.UserValidator;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation for managing user-related actions such as login, registration, and viewing notifications.
 * <p>
 * This servlet handles various HTTP GET and POST requests, including user login, registration, logout, and viewing notifications.
 * It forwards requests to appropriate JSP pages based on the user's action and handles potential errors by displaying error messages.
 * </p>
 * 
 * @author Robin Guan(041117292)
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    
    /**
     * Handles GET requests to navigate to different user views based on the action parameter.
     * 
     * @param request the {@link HttpServletRequest} object that contains the request from the client
     * @param response the {@link HttpServletResponse} object that will be used to send the response to the client
     * @throws ServletException if a servlet error occurs during request processing
     * @throws IOException if an I/O error occurs during request or response handling
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        String page = "index.jsp"; // default

        if ("viewRetailer".equals(action)) {
            page = "views/retailer.jsp";
        } else if ("viewCharity".equals(action)) {
            page = "views/charity.jsp";
        } else if ("viewConsumer".equals(action)) {
            page = "views/consumer.jsp";
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }


    /**
     * Handles POST requests for user login, registration, logout, and viewing notifications.
     * 
     * @param request the {@link HttpServletRequest} object that contains the request from the client
     * @param response the {@link HttpServletResponse} object that will be used to send the response to the client
     * @throws ServletException if a servlet error occurs during request processing
     * @throws IOException if an I/O error occurs during request or response handling
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "login":
                login(request, response);
                break;
            case "openRegisterPage":
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/register.jsp");
                dispatcher.forward(request, response);
                response.getWriter().println("Add Food functionality");
                break;
            case "register":
                register(request,response);
                break;
            case "logout":
                logout(request, response);
                break;
            case "viewPhoneNotification":
                viewNotification(NotificationMethodConstant.PHONE, request,response);
                break;
            case "viewEmailPhoneNotification":
                viewNotification(NotificationMethodConstant.EMAIL, request,response);
                break;
            default:
                response.getWriter().println("Unknown action");
        }  
    }

    /**
     * Handles user login by verifying credentials and setting user session attributes.
     * 
     * @param request the {@link HttpServletRequest} object that contains the request from the client
     * @param response the {@link HttpServletResponse} object that will be used to send the response to the client
     * @throws ServletException if a servlet error occurs during request processing
     * @throws IOException if an I/O error occurs during request or response handling
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = null;
        int[] count = new int[2];
        
        UserService userService = new UserService();
        try {
            user = userService.getUserByCredentials(email, password);
        } catch (DataNotExistsException ex) {
            request.setAttribute("errorMessage", "These credentials do not match our records.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        if(user == null){
            request.setAttribute("errorMessage", "These credentials do not match our records.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        } else{
            // Store user object to HttpSession
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            try {
                getNotificationCount(request);
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            switch(user.getType()) {
                case UserTypeConstant.RETAILER:                    
                    request.getRequestDispatcher("/views/retailer.jsp").forward(request, response);
                    return;                   
                case UserTypeConstant.CONSUMER:
                    request.getRequestDispatcher("/views/consumer.jsp").forward(request, response);
                    return;
                case UserTypeConstant.CHARITY:
                    request.getRequestDispatcher("/views/charity.jsp").forward(request, response);
                    return;
                default:
                    // 处理未知用户类型
                    request.setAttribute("errorMessage", "Unknown user type.");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    return;
            }
        }
        
    }
    
    /**
     * Handles user registration by validating inputs and registering a new user.
     * 
     * @param request the {@link HttpServletRequest} object that contains the request from the client
     * @param response the {@link HttpServletResponse} object that will be used to send the response to the client
     * @throws ServletException if a servlet error occurs during request processing
     * @throws IOException if an I/O error occurs during request or response handling
     */
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String type = request.getParameter("type");
        String organization = request.getParameter("organization");
        
        UserValidator validator = new UserValidator();                
        String[] userData = getRegistrationHistory(request);
    
         if (!validator.validate(firstname, lastname, phone, email, password, repassword, type, organization)) {
            request.setAttribute("userData", userData);
            request.setAttribute("errorMessage", validator.getErrorMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/register.jsp");
            dispatcher.forward(request, response);
            return; // Ensure no further code is executed
        }
        
        UserService userService = new UserService();
         try {
            userService.register(firstname, lastname, phone, email, password, type, organization);
            response.sendRedirect(request.getContextPath() + "/index.jsp?successMessage=Registered%20successfully,%20please%20log%20in.");
        } catch(DataAlreadyExistsException ee) {
            request.setAttribute("errorMessage", "User Already Exists.");
            request.setAttribute("userData", userData);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/register.jsp");
            dispatcher.forward(request, response);
        } catch (DataInsertionFailedException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.setAttribute("userData", userData);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/register.jsp");
            dispatcher.forward(request, response);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("userData", userData);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/register.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    /**
     * Retrieves the registration history from the request parameters.
     * 
     * @param request the {@link HttpServletRequest} object that contains the request from the client
     * @return an array of strings containing registration input data
     */
    private String[] getRegistrationHistory(HttpServletRequest request) {
        String[] inputHistory = new String[8];
        inputHistory[0] = request.getParameter("firstname");
        inputHistory[1] = request.getParameter("lastname");
        inputHistory[2] = request.getParameter("phone");
        inputHistory[3] = request.getParameter("email");
        inputHistory[4] = request.getParameter("type");
        inputHistory[5] = request.getParameter("organization");
        return inputHistory;
    }
    
    /**
     * Handles user logout by invalidating the session and redirecting to the index page with a success message.
     * 
     * @param request the {@link HttpServletRequest} object that contains the request from the client
     * @param response the {@link HttpServletResponse} object that will be used to send the response to the client
     * @throws ServletException if a servlet error occurs during request processing
     * @throws IOException if an I/O error occurs during request or response handling
     */
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        request.setAttribute("successMessage", "You have been successfully logged out.");
        response.sendRedirect(request.getContextPath() + "/index.jsp?errorMessage=You have been logged out.");
    }
     
    /**
     * Retrieves and sets the notification counts in the session for the logged-in user.
     * 
     * @param request the {@link HttpServletRequest} object that contains the request from the client
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if a class cannot be found
     */
    private void getNotificationCount(HttpServletRequest request) throws SQLException, ClassNotFoundException{
        HttpSession session = request.getSession(false);
        int count[] = new int[2];
        int phoneNotificationCount = 0;
        int emailNotificationCount = 0;
        UserService userService = new UserService();
        
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                count = userService.getNotificationCount(user);
                // 假设你有获取通知计数的方法
                emailNotificationCount = count[0];
                phoneNotificationCount = count[1];
                
                session.setAttribute("phoneNotificationCount", phoneNotificationCount);
                session.setAttribute("emailNotificationCount", emailNotificationCount);
            }
        }
       
    }
     
    /**
     * Handles viewing notifications based on the specified method (phone or email).
     * 
     * @param method the notification method (phone or email)
     * @param request the {@link HttpServletRequest} object that contains the request from the client
     * @param response the {@link HttpServletResponse} object that will be used to send the response to the client
     * @throws ServletException if a servlet error occurs during request processing
     * @throws IOException if an I/O error occurs during request or response handling
     */
    private void viewNotification(int method, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();        
        HttpSession session = request.getSession(false);
        User user = null;
        int count = 0;

        if (session != null) {
            user = (User) session.getAttribute("user");
        }

        if (user != null) {
            try {
                ArrayList<Notification> notifications = userService.getNotifications(user, method);
                count = notifications.size();
                request.setAttribute("count", count);
                request.setAttribute("notifications", notifications);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/notification.jsp");
                dispatcher.forward(request, response);

            } catch (SQLException | ClassNotFoundException ex) {
                int userType = user.getType(); // 假设 getType() 返回的是 int
                switch (userType) {
                    case UserTypeConstant.RETAILER:
                        {
                            request.setAttribute("errorMessage", ex.getMessage());
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/retailer.jsp");
                            dispatcher.forward(request, response);
                            break;
                        }
                    case UserTypeConstant.CONSUMER:
                        {
                            request.setAttribute("errorMessage", ex.getMessage());
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/consumer.jsp");
                            dispatcher.forward(request, response);
                            break;
                        }
                    case UserTypeConstant.CHARITY:
                        {
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/charity.jsp");
                            dispatcher.forward(request, response);
                            break;
                        }
                    default:
                        // 处理未知用户类型
                        request.setAttribute("errorMessage", "Unknown user type.");
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                        break;
                }
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
