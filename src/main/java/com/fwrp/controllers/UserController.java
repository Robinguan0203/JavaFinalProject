/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers;

import com.fwrp.controllers.usercommand.IUserCommand;
import com.fwrp.controllers.usercommand.UserCommandFactory;
import com.fwrp.controllers.userstrategy.NavigationStrategySelector;
import com.fwrp.models.User;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation for managing user-related actions such as login, registration, and viewing notifications.
 * 
 * This servlet handles various HTTP GET and POST requests, including user login, registration, logout, and viewing notifications.
 * It forwards requests to appropriate JSP pages based on the user's action and handles potential errors by displaying error messages.
 * 
 * Author: Robin Guan(041117292)
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
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
        NavigationStrategySelector strategySelector = new NavigationStrategySelector();
        strategySelector.selectNavigationStrategy(action);
        strategySelector.navigate(request, response);
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
        IUserCommand command = UserCommandFactory.getCommand(action);
        if (command != null) {
            command.execute(request, response);
        } else {
            response.getWriter().println("Unknown action");
        }
        
        //User user = this.getUserFromSession(request);
        //request.setAttribute("user", user);
    }

	/**
     * Retrieves the User object from the session.
     * 
     * @param request the {@link HttpServletRequest} object that contains the request from the client
     * @return the User object from the session, or null if no session exists or the attribute is not found
     */
    private User getUserFromSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (User) session.getAttribute("user");
        }
        return null;
    }
    
    /**
     * Enum representing different method types for user notifications.
     */
    public enum MethodType {
        
        /**
        * Email notification method.
        * Code: 1
        * Description: EMAIL
        */
        EMAIL(1, "EMAIL"),
        
        /**
        * Phone notification method.
        * Code: 2
        * Description: PHONE
        */
        PHONE(2, "PHONE"),
        
        /**
        * System notification method.
        * Code: 3
        * Description: SYSTEM
        */
        SYSTEM(3, "SYSTEM");

        private final int code;
        private final String description;

        /**
        * Constructor for MethodType enum.
        *
        * @param code        the code representing the method type
        * @param description the description of the method type
        */
        MethodType(int code, String description) {
            this.code = code;
            this.description = description;
        }

        /**
        * Gets the code of the method type.
        *
        * @return the code of the method type
        */
        public int getCode() {
            return code;
        }

        /**
        * Gets the description of the method type.
        *
        * @return the description of the method type
        */
        public String getDescription() {
            return description;
        }

        /**
        * Gets the description of the method type by its code.
        *
        * @param code the code of the method type
        * @return the description of the method type, or "Unknown" if the code does not match any method type
        */
        public static String getDescriptionByCode(int code) {
            for (MethodType type : values()) {
                if (type.getCode() == code) {
                    return type.getDescription();
                }
            }
            return "Unknown";
        }
    }
}