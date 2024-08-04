/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers;

import com.fwrp.controllers.retailercommand.IRetailerCommand;
import com.fwrp.controllers.retailercommand.RetailerCommandFactory;
import com.fwrp.controllers.usercommand.UserCommandFactory;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.exceptions.DataNotExistsException;
import com.fwrp.exceptions.NegativeInventoryException;
import com.fwrp.models.ExpireInfo;
import com.fwrp.models.Food;
import com.fwrp.models.Retailer;
import com.fwrp.models.Transaction;
import com.fwrp.models.User;
import com.fwrp.services.RetailerService;
import com.fwrp.services.UserService;
import com.fwrp.validator.ExpireDateValidator;
import com.fwrp.validator.FoodExpireDaysValidator;
import com.fwrp.validator.FoodQuantityValidator;
import com.fwrp.validator.FoodValidator;
import com.fwrp.validator.IsSurplusValidator;
import com.fwrp.validator.SurplusValidator;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
 * Controller for handling retailer-related requests.
 * 
 * This servlet processes POST requests, checks for user authentication, 
 * retrieves the retailer from the session, and executes the appropriate 
 * command based on the action parameter.
 * 
 * Author: Robin Guan
 * Version: 1.0
 * Since: 17.0.8
 */
@WebServlet("/RetailerController")
public class RetailerController extends HttpServlet {    
    
	/**
     * Processes POST requests.
     * 
     * @param request  The HttpServletRequest object that contains the request the client made to the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); 

        if (session == null || session.getAttribute("user") == null) {
            request.setAttribute("errorMessage", "You must be logged in first.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return; 
        }

        Retailer retailer = this.getRetailerFromSession(request);
        request.setAttribute("retailer", retailer);

        String action = request.getParameter("action");
        IRetailerCommand command = RetailerCommandFactory.getCommand(action);

        if (command != null) {
            command.execute(request, response);
        } else {
            response.getWriter().println("Unknown action");
        }
    }

	/**
     * Retrieves the Retailer object from the session.
     * 
     * @param request The HttpServletRequest object that contains the request the client made to the servlet.
     * @return The Retailer object from the session, or null if not found.
     */
    private Retailer getRetailerFromSession(HttpServletRequest request) {
        return (Retailer) request.getSession().getAttribute("retailer");
    }
}
    
    