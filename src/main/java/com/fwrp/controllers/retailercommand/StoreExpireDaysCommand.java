/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.retailercommand;

import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.services.RetailerService;
import com.fwrp.validator.FoodExpireDaysValidator;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author robin
 */
public class StoreExpireDaysCommand  implements IRetailerCommand{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int foodId = Integer.parseInt(request.getParameter("foodId"));
        int expireDays = Integer.parseInt(request.getParameter("expireDays"));
        
        FoodExpireDaysValidator validator = new FoodExpireDaysValidator();
        if (!validator.validate(foodId, expireDays)) {
            request.setAttribute("errorMessage", validator.getErrorMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/changeExpireDays.jsp");
            dispatcher.forward(request, response);
            return;
        }

        RetailerService retailerService = new RetailerService();
        try {
            retailerService.storeFoodExpireDays(foodId, expireDays);
            response.sendRedirect(request.getContextPath() + "/views/retailer.jsp?successMessage=Food%20expire%20days%20updated%20successfully.");
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/changeExpireDays.jsp");
            dispatcher.forward(request, response);
        } catch (DataInsertionFailedException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/changeExpireDays.jsp");
            dispatcher.forward(request, response);
        } 
    }
}