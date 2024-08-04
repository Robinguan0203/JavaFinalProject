/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers;

import com.fwrp.constants.FileLocationConstant;
import com.fwrp.constants.NotificationMethodConstant;
import com.fwrp.constants.UserTypeConstant;
import com.fwrp.controllers.usercommand.IUserCommand;
import com.fwrp.controllers.usercommand.UserCommandFactory;
import com.fwrp.controllers.userstrategy.INavigationStrategy;
import com.fwrp.controllers.userstrategy.NavigationStrategySelector;
import com.fwrp.dataaccess.DataSource;
import static com.fwrp.dataaccess.DataSource.openPropsFile;

import com.fwrp.dataaccess.dto.SubscriptionDTO;
import com.fwrp.dbService.UserDbService;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.exceptions.DataNotExistsException;
import com.fwrp.models.Charity;
import com.fwrp.models.Consumer;
import com.fwrp.models.Notification;
import com.fwrp.models.Retailer;
import com.fwrp.models.User;
import com.fwrp.services.ConsumerService;
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
import java.util.List;
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

        switch (action) {
            case "manageSubscription":
                manageSubscription(request,response);
                break;
            case "showAddSubscription":
                showAddSubscription(request,response);
                break;
            case "addSubscription":
                addSubscription(request,response);
                break;
            case "deleteSubscription":
                deleteSubscription(request,response);
                break;
            default:
                if (command != null) {
                    command.execute(request, response);
                } else {
                    response.getWriter().println("Unknown action");
                }
        }


        User user = this.getUserFromSession(request);
        request.setAttribute("user", user);
    }

    private User getUserFromSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (User) session.getAttribute("user");
        }
        return null;
    }

    private void manageSubscription(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/manageSubscription.jsp");
        User user=this.getUserFromSession(req);
        UserService userService = new UserService();
        try {
            List<SubscriptionDTO> subscriptionDTOList=userService.getAllMethodsByUserId(user.getId());
            req.setAttribute("subscriptionList", subscriptionDTOList);
        } catch(ClassNotFoundException | SQLException ee){
            req.setAttribute("errorMessage", ee.getMessage());
            dispatcher.forward(req, resp);
        }
        dispatcher.forward(req, resp);
        resp.getWriter().println("Manage Subscription");
    }

    private void showAddSubscription(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/addSubscription.jsp");

        dispatcher.forward(req, resp);
        resp.getWriter().println("Show Add Subscription");
    }

    private void addSubscription(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int method = Integer.parseInt(req.getParameter("method"));

        User user=this.getUserFromSession(req);
        UserService userService = new UserService();
        try {
            userService.addSubscription(user.getId(),method);
        } catch(DataAlreadyExistsException ee){
            req.setAttribute("errorMessage", "Subscription Already Exist.");
        } catch (DataInsertionFailedException ex) {
            req.setAttribute("errorMessage", ex.getMessage());
        } catch (Exception e){
            req.setAttribute("errorMessage", e.getMessage());
        }
        manageSubscription(req,resp);
    }
    public enum MethodType {
        EMAIL(1, "EMAIL"),
        PHONE(2, "PHONE"),
        SYSTEM(3, "SYSTEM");

        private final int code;
        private final String description;

        MethodType(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

        public static String getDescriptionByCode(int code) {
            for (MethodType type : values()) {
                if (type.getCode() == code) {
                    return type.getDescription();
                }
            }
            return "Unknown";
        }
    }

    private void deleteSubscription(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));

        UserService userService = new UserService();
        try {
            userService.deleteSubscription(id);
        } catch (Exception ex) {
            req.setAttribute("errorMessage", ex.getMessage());
        }
        manageSubscription(req,resp);
    }
}