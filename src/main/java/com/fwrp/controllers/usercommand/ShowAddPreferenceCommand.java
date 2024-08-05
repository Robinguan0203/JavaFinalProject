/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.usercommand;

import com.fwrp.dataaccess.dto.PreferenceDTO;
import com.fwrp.models.Food;
import com.fwrp.models.User;
import com.fwrp.services.RetailerService;
import com.fwrp.services.UserService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command to show the add subscription page.
 * 
 * This class implements the IUserCommand interface and handles the 
 * execution of displaying the add subscription page by forwarding 
 * the request to the addSubscription.jsp page.
 * 
 * Author: Robin Guan
 * Version: 1.0
 * Since: 17.0.8
 */
public class ShowAddPreferenceCommand implements IUserCommand{
	
	/**
     * Executes the command to show the add subscription page.
     * 
     * @param req   The HttpServletRequest object that contains the request the client made to the servlet.
     * @param resp  The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RetailerService retailerService = new RetailerService();
        ArrayList<Food> foods = new ArrayList<>();
        try {
            foods = retailerService.getAllFoods();
        } catch (ClassNotFoundException | SQLException ex) {
            req.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/consumer.jsp");
            dispatcher.forward(req, resp);
        } 
        
        req.setAttribute("foods", foods);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/consumer/addPreference.jsp");
        
        dispatcher.forward(req, resp);
        
    }
}