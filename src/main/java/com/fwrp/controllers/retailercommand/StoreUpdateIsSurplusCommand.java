/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.retailercommand;

import com.fwrp.exceptions.DataNotExistsException;
import com.fwrp.models.ExpireInfo;
import com.fwrp.services.RetailerService;
import com.fwrp.validator.IsSurplusValidator;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to update the surplus status of food items for a retailer.
 * 
 * This class implements the IRetailerCommand interface and handles the 
 * execution of updating surplus status by validating input parameters and 
 * interacting with the RetailerService.
 *
 * Author: Robin Guan
 * Version: 1.0
 * Since: 17.0.8
 */
public class StoreUpdateIsSurplusCommand implements IRetailerCommand{

	/**
     * Executes the command to update the surplus status of food items.
     * 
     * @param request  The HttpServletRequest object that contains the request the client made to the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        RetailerService retailerService = new RetailerService();
         
        ArrayList<ExpireInfo> expireInfos = (ArrayList<ExpireInfo>) request.getAttribute("expireInfos");                         
         
         String action = request.getParameter("action");
         if ("storeUpdateIsSurplus".equals(action)) {
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                String paramName = entry.getKey();
                String[] paramValues = entry.getValue();
                boolean newIsSurplus;

                if (paramName.startsWith("newIsSurplus_")) {
                    String idStr = paramName.substring("newIsSurplus_".length());    
                    int id = Integer.parseInt(idStr);
                    String newIsSurplusStr  = paramValues[0]; 
                    
                    IsSurplusValidator validator = new IsSurplusValidator();
                    if (!validator.validate(newIsSurplusStr)) {
                        request.setAttribute("expireInfos", expireInfos); // Set expireInfos
                        request.setAttribute("errorMessage", validator.getErrorMessage());
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/markSurplus.jsp");
                        dispatcher.forward(request, response);
                        return;
                    }
                    
                    if(newIsSurplusStr.equalsIgnoreCase("0")){
                        newIsSurplus = false;
                    }else{
                        newIsSurplus = true;
                    }

                    try {
                        retailerService.updateIsSurplusOfExpireInfo(id, newIsSurplus);  
                    } catch (SQLException | ClassNotFoundException ex) {
                        request.setAttribute("expireInfos", expireInfos); // Set expireInfos
                        request.setAttribute("errorMessage", ex.getMessage());
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/markSurplus.jsp");
                        dispatcher.forward(request, response);
                        return; 
                    } catch (DataNotExistsException ex) {
                        request.setAttribute("expireInfos", expireInfos); // Set expireInfos
                        request.setAttribute("errorMessage", ex.getMessage());
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/markSurplus.jsp");
                        dispatcher.forward(request, response);
                        return; 
                    } 
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/views/retailer.jsp?successMessage=Food%20expire%20dates%20updated%20successfully.");
    }
}
