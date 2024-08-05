/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.retailercommand;

import com.fwrp.exceptions.NegativeInventoryException;
import com.fwrp.models.Retailer;
import com.fwrp.services.RetailerService;
import com.fwrp.validator.FoodQuantityValidator;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class implements the {@link IRetailerCommand} interface and handles the addition of food quantities.
 * 
 *
 * @throws ServletException if the request could not be handled
 * @throws IOException if an input or output error is detected when the servlet handles the request
 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public class StoreAddQuantityCommand  implements IRetailerCommand{

	/**
     * Executes the command to add food quantities.
     * 
     * @param request the {@link HttpServletRequest} object that contains the request the client made to the servlet
     * @param response the {@link HttpServletResponse} object that contains the response the servlet returns to the client
     * @throws ServletException if the request could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the request
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int foodId = Integer.parseInt(request.getParameter("foodId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        FoodQuantityValidator validator = new FoodQuantityValidator();
        if (!validator.validate(foodId, quantity)) {
            request.setAttribute("errorMessage", validator.getErrorMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/addQuantities.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        RetailerService retailerService = new RetailerService();
        Retailer retailer = this.getRetailerFromSession(request);
        
        try {
            retailerService.addFoodQuantities(foodId, quantity, retailer);
            response.sendRedirect(request.getContextPath() + "/views/retailer.jsp?successMessage=Food%20quantity%20updated%20successfully.");
        } catch (NegativeInventoryException ex) {
            request.setAttribute("errorMessage", "Quantity to deduct is greater than inventory.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/addQuantities.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/addQuantities.jsp");
            dispatcher.forward(request, response);
        } 
    }
    
	/**
     * Retrieves the retailer object from the session.
     * 
     * @param request the {@link HttpServletRequest} object that contains the request the client made to the servlet
     * @return the {@link Retailer} object from the session, or null if not found
     */
    private Retailer getRetailerFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Retailer) session.getAttribute("user");
        }
        return null;
    }
}
