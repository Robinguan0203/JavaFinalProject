/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.retailercommand;

import com.fwrp.exceptions.DataNotExistsException;
import com.fwrp.models.ExpireInfo;
import com.fwrp.models.Food;
import com.fwrp.services.RetailerService;
import com.fwrp.validator.ExpireDateValidator;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author robin
 */
public class StoreUpdateExpireDateCommand implements IRetailerCommand{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        RetailerService retailerService = new RetailerService();
         
        ArrayList<ExpireInfo> expireInfos = buildExpireInfoExpireDate(request);                
         
         String action = request.getParameter("action");
         if ("storeUpdateExpireDate".equals(action)) {
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                String paramName = entry.getKey();
                String[] paramValues = entry.getValue();
                Date newExpireDate = null;

                if (paramName.startsWith("newExpireDate_")) {
                    String idStr = paramName.substring("newExpireDate_".length());
    
                    int id = Integer.parseInt(idStr);
                    String newExpireDateStr  = paramValues[0]; 
                    if (newExpireDateStr != null && !newExpireDateStr.trim().isEmpty()) {
                        try {
                            newExpireDate = dateFormat.parse(newExpireDateStr);
                            
                            ExpireDateValidator validator = new ExpireDateValidator();
                            if (!validator.validate(newExpireDate)) {
                                request.setAttribute("expireInfos", expireInfos); // Set expireInfos
                                request.setAttribute("errorMessage", validator.getErrorMessage());
                                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/updateExpireDate.jsp");
                                dispatcher.forward(request, response);
                                return;
                            }

                            retailerService.updateExpireDateOfExpireInfo(id, newExpireDate);
                            
                        } catch (ParseException ex) {
                            request.setAttribute("expireInfos", expireInfos); // Set expireInfos
                            request.setAttribute("errorMessage", "Wrong Date Format.");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/updateExpireDate.jsp");
                            dispatcher.forward(request, response);
                            return; 
                        } catch (NumberFormatException e) {
                            request.setAttribute("expireInfos", expireInfos); // Set expireInfos
                            request.setAttribute("errorMessage", "Wrong Number format.");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/updateExpireDate.jsp");
                            dispatcher.forward(request, response);
                            return; 
                        } catch (SQLException | ClassNotFoundException ex) {
                            request.setAttribute("expireInfos", expireInfos); // Set expireInfos
                            request.setAttribute("errorMessage", ex.getMessage());
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/updateExpireDate.jsp");
                            dispatcher.forward(request, response);
                            return; 
                        } catch (DataNotExistsException ex) {
                            request.setAttribute("expireInfos", expireInfos); // Set expireInfos
                            request.setAttribute("errorMessage", ex.getMessage());
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/updateExpireDate.jsp");
                            dispatcher.forward(request, response);
                            return; 
                        } 
                    }
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/views/retailer.jsp?successMessage=Food%20expire%20dates%20updated%20successfully.");
    }
    
    private ArrayList<ExpireInfo> buildExpireInfoExpireDate(HttpServletRequest request){
        String[] expireInfoIds = request.getParameterValues("expireInfoId");
        String[] foodIds = request.getParameterValues("foodId");
        String[] foodNames = request.getParameterValues("foodName");
        String[] foodExpireDays = request.getParameterValues("foodExpireDays");
        String[] foodUnitprices = request.getParameterValues("foodUnitprice");
        String[] foodDiscounts = request.getParameterValues("foodDiscount");
        String[] quantities = request.getParameterValues("quantity");
        String[] expireDates = request.getParameterValues("expireDate");
        String[] isSurpluses = request.getParameterValues("isSurplus");
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");        
        ArrayList<ExpireInfo> list = new ArrayList<>();
        for (int i = 0; i < expireInfoIds.length; i++) {
            Food food = new Food();
            food.setId(Integer.parseInt(foodIds[i]));
            food.setName(foodNames[i]);
            food.setExpireDays(Integer.parseInt(foodExpireDays[i]));
            try {
                food.setUnitPrice(Double.parseDouble(foodUnitprices[i]));
            } catch (NumberFormatException e) {
                food.setUnitPrice(0.0);
            }

            try {
                food.setDiscount(Double.parseDouble(foodDiscounts[i]));
            } catch (NumberFormatException e) {
                food.setDiscount(0.0);
            }
            
            ExpireInfo info = new ExpireInfo();
            info.setId(Integer.parseInt(expireInfoIds[i]));
            info.setFood(food);
            info.setQuantity(Integer.parseInt(quantities[i]));
            
            try {
                Date expireDate = dateFormat.parse(expireDates[i]);
                info.setExpireDate(expireDate);
            } catch (ParseException e) {                
                info.setExpireDate(null); 
            }
            
            info.setIsSurplus(Boolean.parseBoolean(isSurpluses[i]));
            
            list.add(info);
        }

        return list;
                                                    
    }
}
