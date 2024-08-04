/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.retailercommand;

import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.services.RetailerService;
import com.fwrp.validator.FoodValidator;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to store new food items for a retailer.
 * 
 * <p>This class implements the IRetailerCommand interface and handles the 
 * execution of storing new food items by validating input parameters and 
 * interacting with the RetailerService.</p>
 * 
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public class StoreNewFoodCommand  implements IRetailerCommand{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String expireDaysParam = request.getParameter("expireDays");
        String unitPriceParam = request.getParameter("unitPrice");
        String discountParam = request.getParameter("discount");
        
        int expireDays = 0;
        double unitPrice = 0.0;
        double discount = 0.0;
        
        // Validate and parse expireDays
        if (expireDaysParam != null && !expireDaysParam.isEmpty()) {
            try {
                expireDays = Integer.parseInt(expireDaysParam);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid expireDays format.");
                request.getRequestDispatcher("/views/food/add.jsp").forward(request, response);
                return;
            }
        } else {
            request.setAttribute("errorMessage", "expireDays is required.");
            request.getRequestDispatcher("/views/food/add.jsp").forward(request, response);
            return;
        }

        // Validate and parse unitPrice
        if (unitPriceParam != null && !unitPriceParam.isEmpty()) {
            try {
                unitPrice = Double.parseDouble(unitPriceParam);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid unitPrice format.");
                request.getRequestDispatcher("/views/food/add.jsp").forward(request, response);
                return;
            }
        } else {
            request.setAttribute("errorMessage", "unitPrice is required.");
            request.getRequestDispatcher("/views/food/add.jsp").forward(request, response);
            return;
        }

        // Validate and parse discount
        if (discountParam != null && !discountParam.isEmpty()) {
            try {
                discount = Double.parseDouble(discountParam);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid discount format.");
                request.getRequestDispatcher("/views/food/add.jsp").forward(request, response);
                return;
            }
        } else {
            request.setAttribute("errorMessage", "discount is required.");
            request.getRequestDispatcher("/views/food/add.jsp").forward(request, response);
            return;
        }
        
        String[] foodData = getFoodInputHistory(request);
        
        FoodValidator validator = new FoodValidator();
        if (!validator.validate(name, expireDays, unitPrice, discount)) {
            request.setAttribute("errorMessage", validator.getErrorMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/add.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
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

    /**
    * Retrieves the food input history from the HTTP request.
    * This method extracts the parameters "name", "expireDays",
    * "unitPrice", and "discount" from the request and returns
    * them as a String array.
    *
    * @param request the HttpServletRequest from which the parameters are extracted
    * @return a String array containing the values of "name", "expireDays",
    *         "unitPrice", and "discount" parameters from the request
    */
    protected  String[] getFoodInputHistory(HttpServletRequest request) {
        String[] inputHistory = new String[4];
        inputHistory[0] = request.getParameter("name");
        inputHistory[1] = request.getParameter("expireDays");
        inputHistory[2] = request.getParameter("unitPrice");
        inputHistory[3] = request.getParameter("discount");

        return inputHistory;
    }
}
