/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.usercommand;

import com.fwrp.constants.UserTypeConstant;
import com.fwrp.exceptions.DataNotExistsException;
import com.fwrp.models.User;
import com.fwrp.services.UserService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command to handle user login.
 * 
 * This class implements the IUserCommand interface and handles the 
 * execution of user login by validating credentials, managing session,
 * and forwarding to the appropriate user page.
 * 
 * Author: Robin Guan
 * Version: 1.0
 * Since: 17.0.8
 */
public class LoginCommand implements IUserCommand{
	
	/**
     * Executes the command to handle user login.
     * 
     * @param request  The HttpServletRequest object that contains the request the client made to the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = null;
        int[] count = new int[2];
        
        UserService userService = new UserService();
        try {
            user = userService.getUserByCredentials(email, password);
        } catch (DataNotExistsException ex) {
            handleError(request, response, "These credentials do not match our records.");
            return;
        } catch (SQLException | ClassNotFoundException ex) {
            handleError(request, response, "An internal error occurred. Please try again later.");
            return;
        }
        
        if(user == null){
            handleError(request, response, "These credentials do not match our records.");            
            return;
        } else{
            // Store user object to HttpSession
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            try {
                getNotificationCount(request);
            } catch (SQLException | ClassNotFoundException ex) {
                handleError(request, response, "Failed to get notifications.");  
            }
            forwardToUserPage(request, response, user);
        }
    }
    
	/**
     * Handles errors by setting an error message and forwarding to the index page.
     * 
     * @param request       The HttpServletRequest object that contains the request the client made to the servlet.
     * @param response      The HttpServletResponse object that contains the response the servlet returns to the client.
     * @param errorMessage  The error message to be displayed.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
     
	/**
     * Forwards the request to the appropriate user page based on user type.
     * 
     * @param request  The HttpServletRequest object that contains the request the client made to the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
     * @param user     The user object containing user details.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    private void forwardToUserPage(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        switch (user.getType()) {
            case UserTypeConstant.RETAILER:
                request.getRequestDispatcher("/views/retailer.jsp").forward(request, response);
                break;
            case UserTypeConstant.CONSUMER:
                request.getRequestDispatcher("/views/consumer.jsp").forward(request, response);
                break;
            case UserTypeConstant.CHARITY:
                request.getRequestDispatcher("/views/charity.jsp").forward(request, response);
                break;
            default:
                handleError(request, response, "Unknown user type.");
                break;
        }
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
        int phoneNotificationCount = 0;
        int emailNotificationCount = 0;
        int systemNotificationCount = 0;
        UserService userService = new UserService();
        
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                int[] count = userService.getNotificationCountByUser(user);
                emailNotificationCount = count[0];
                phoneNotificationCount = count[1];
                systemNotificationCount = count[2];
                
                session.setAttribute("phoneNotificationCount", phoneNotificationCount);
                session.setAttribute("emailNotificationCount", emailNotificationCount);
                session.setAttribute("systemNotificationCount", systemNotificationCount);
            }
        }
       
    }
}
