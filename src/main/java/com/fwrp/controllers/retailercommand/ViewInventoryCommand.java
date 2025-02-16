/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.retailercommand;

import com.fwrp.models.Food;
import com.fwrp.services.RetailerService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to view the inventory for a retailer.
 * 
 * This class implements the IRetailerCommand interface and handles the 
 * execution of viewing the inventory by retrieving all inventory data 
 * and forwarding the request to the appropriate view.
 * 
 * Author: Robin Guan
 * Version: 1.0
 * Since: 17.0.8
 */
public class ViewInventoryCommand implements IRetailerCommand{

	/**
     * Executes the command to view the inventory.
     * 
     * @param request  The HttpServletRequest object that contains the request the client made to the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RetailerService retailerService = new RetailerService();
        
        
        try {
            HashMap<Food, Integer[]> foodInventoryMap = retailerService.getAllInventoryData();
            int count = foodInventoryMap.size();
            request.setAttribute("count", count);
            request.setAttribute("foodInventoryMap", foodInventoryMap);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/inventory.jsp");
            dispatcher.forward(request, response);
 
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/retailer.jsp");            
            dispatcher.forward(request, response);
            return;
        }
    }
}
