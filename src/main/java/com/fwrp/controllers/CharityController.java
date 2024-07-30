/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers;

import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.exceptions.DataNotExistsException;
import com.fwrp.exceptions.NegativeInventoryException;
import com.fwrp.models.ExpireInfo;
import com.fwrp.models.Food;
import com.fwrp.models.Charity;
import com.fwrp.models.Transaction;
import com.fwrp.models.User;
import com.fwrp.services.CharityService;
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
 *
 * @author Ke Yan
 */
@WebServlet("/CharityController")
public class CharityController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); 
        
        if (session == null || session.getAttribute("user") == null) {
            request.setAttribute("errorMessage", "You must be logged in first.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return; 
        }
        
        Charity charity = this.getCharityFromSession(request);
        request.setAttribute("charity", charity);
        
        String action = request.getParameter("action");
        switch (action) {
            case "checkInventory":
                checkInventory(request, response);
                break;
            case "createClaim":
                createClaim(request,response);
                break;           
            default:
                response.getWriter().println("Unknown action");
        }
    }
    
    private Charity getCharityFromSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Charity) session.getAttribute("user");
        }
        return null;
    }   

    private void checkInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        CharityService charityService = new CharityService();
        
        
        try {
            HashMap<Food, Integer[]> foodInventoryMap = charityService.getAllInventoryData();
            int count = foodInventoryMap.size();
            request.setAttribute("count", count);
            request.setAttribute("foodInventoryMap", foodInventoryMap);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/inventory.jsp");
            dispatcher.forward(request, response);
 
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/charity.jsp");            
            dispatcher.forward(request, response);
            return;
        }
    }
    
    private void createClaim(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/charity/claim.jsp");
        dispatcher.forward(request, response);
        response.getWriter().println("Create claim functionality");
    }
    
    private void storeNewClaim(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String name = request.getParameter("name");
        int expireDays = Integer.parseInt(request.getParameter("expireDays"));
        double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        
        String[] claimData = getClaimInputHistory(request);       
        
        RetailerService retailerService = new RetailerService();
        try {
            retailerService.storeNewFood(name, expireDays, unitPrice, discount);
            response.sendRedirect(request.getContextPath() + "/views/retailer.jsp?successMessage=Food%20added%20successfully.");
        } catch(DataAlreadyExistsException ee){
            request.setAttribute("errorMessage", "Food Already Exist.");
            request.setAttribute("foodData", foodData);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/add.jsp");
            dispatcher.forward(request, response);
        } catch (DataInsertionFailedException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.setAttribute("foodData", foodData);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/add.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e){
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("foodData", foodData);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/add.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private String[] getClaimInputHistory(HttpServletRequest request) {
        String[] inputHistory = new String[4];
        inputHistory[0] = request.getParameter("name");
        inputHistory[1] = request.getParameter("expireDays");
        inputHistory[2] = request.getParameter("unitPrice");
        inputHistory[3] = request.getParameter("discount");

        return inputHistory;
    }
}


