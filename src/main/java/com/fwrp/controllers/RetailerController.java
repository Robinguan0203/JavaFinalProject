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
 * Servlet implementation class RetailerController
 * <p>
 * This servlet is responsible for handling requests related to retailer operations.
 * It manages actions such as adding food, updating inventory, and handling expiration information.
 * <p>
 * <b>URL Mapping:</b> /RetailerController
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 */
@WebServlet("/RetailerController")
public class RetailerController extends HttpServlet {
    
    /**
     * Handles HTTP POST requests. Based on the 'action' parameter in the request,
     * it delegates the processing to specific methods.
     * <p>
     * If the session does not exist or the user is not logged in, the user is redirected
     * to the login page with an error message. Otherwise, it processes the request based on
     * the action parameter.
     * </p>
     * 
     * @param request  the HttpServletRequest object that contains the request from the client
     * @param response the HttpServletResponse object that contains the response to send to the client
     * @throws ServletException if an error occurs while handling the request
     * @throws IOException      if an I/O error occurs while handling the request
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
        switch (action) {
            case "addFood":
                addFood(request, response);
                break;
            case "storeNewFood":
                storeNewFood(request,response);
                break;
            case "addQuantities":
                addQuantities(request,response);
                break;
            case "storeAddQuantity":
                storeAddQuantity(request,response,retailer);
                break;
            case "changeFoodExpireDays":
                changeFoodExpireDays(request,response);
                break;
            case "storeExpireDays":
                storeExpireDays(request,response);
                break;
            case "updateInventoryExpireDate":
                updateInventoryExpireDate(request, response);
                break;
            case "storeUpdateExpireDate":
                storeUpdateExpireDate(request,response);
                break;
            case "identifySurplus":
                identifySurplus(request, response);
                break;
            case "storeUpdateIsSurplus":
                storeUpdateIsSurplus(request, response);
                break;
            case "listSurplus":
                listSurplus(request, response);
                break;
            case "storeListSurplus":
                storeListSurplus(request, response, retailer);
                break;
            case "viewInventory":
                viewInventory(request, response);
                break;
            case "viewTransactions":
                //viewTransactionsrequest, response);
                break;
            default:
                response.getWriter().println("Unknown action");
        }
    }
    
    /**
    * Retrieves the {@link Retailer} object from the current HTTP session.
    * <p>
    * This method attempts to get the current session without creating a new one. If the session exists
    * and contains an attribute named "user", it returns the {@link Retailer} object associated with that
    * attribute. If the session does not exist or does not contain the "user" attribute, it returns null.
    * </p>
    * 
    * @param request the {@link HttpServletRequest} object that contains the request from the client
    * @return the {@link Retailer} object from the session, or null if the session is invalid or does not
    *         contain the "user" attribute
    */
    private Retailer getRetailerFromSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Retailer) session.getAttribute("user");
        }
        return null;
    }    
    
    /**
    * Handles the HTTP POST request to display the "Add Food" form.
    * <p>
    * This method forwards the request to the "add.jsp" page located under the "/views/food/" directory,
    * which is intended for adding new food items. After forwarding the request, it writes a message to
    * the response indicating that the "Add Food" functionality is being handled. Note that the message 
    * may not be visible to the user if the request is successfully forwarded, as the response will be
    * processed by the forwarded JSP page.
    * </p>
    * 
    * @param request the {@link HttpServletRequest} object that contains the request from the client
    * @param response the {@link HttpServletResponse} object that will be used to return the response to the client
    * @throws IOException if an input or output error occurs while handling the request
    * @throws ServletException if the request forwarding or handling fails
    */
    private void addFood(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/add.jsp");
        dispatcher.forward(request, response);
        response.getWriter().println("Add Food functionality");
    }
    
    private void storeNewFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String name = request.getParameter("name");
        int expireDays = Integer.parseInt(request.getParameter("expireDays"));
        double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        
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
    * Handles the HTTP POST request to store new food information.
    * <p>
    * This method retrieves food details from the request parameters, validates the input using the
    * {@link FoodValidator}, and then attempts to store the new food information using the 
    * {@link RetailerService}. If validation or data storage fails, it forwards the request back to the
    * "add.jsp" page with appropriate error messages. If successful, it redirects the user to the 
    * "retailer.jsp" page with a success message.
    * </p>
    * 
    * @param request the {@link HttpServletRequest} object that contains the request from the client
    * @param response the {@link HttpServletResponse} object that will be used to return the response to the client
    * @throws ServletException if an error occurs while processing the request or forwarding to another page
    * @throws IOException if an input or output error occurs while handling the request or redirecting the response
    */
    private String[] getFoodInputHistory(HttpServletRequest request) {
        String[] inputHistory = new String[4];
        inputHistory[0] = request.getParameter("name");
        inputHistory[1] = request.getParameter("expireDays");
        inputHistory[2] = request.getParameter("unitPrice");
        inputHistory[3] = request.getParameter("discount");

        return inputHistory;
    }
    
    /**
    * Handles the HTTP request to display the page for adding quantities to food items.
    * <p>
    * This method retrieves the list of all food items from the {@link RetailerService} and forwards
    * the request to the "addQuantities.jsp" page with the list of foods as an attribute. If there
    * is an error retrieving the food items (e.g., due to a database connection issue), it sets an
    * error message and forwards the request to the "retailer.jsp" page.
    * </p>
    * 
    * @param request the {@link HttpServletRequest} object that contains the request from the client
    * @param response the {@link HttpServletResponse} object that will be used to return the response to the client
    * @throws ServletException if an error occurs while processing the request or forwarding to another page
    * @throws IOException if an input or output error occurs while handling the request or forwarding the response
    */
    private void addQuantities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RetailerService retailerService = new RetailerService();
        ArrayList<Food> foods = new ArrayList<>();
        try {
            foods = retailerService.getAllFoods();
        } catch (ClassNotFoundException | SQLException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/retailer.jsp");
            dispatcher.forward(request, response);
        } 
        
        request.setAttribute("foods", foods);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/addQuantities.jsp");
        dispatcher.forward(request, response);
    }
    
    /**
    * Processes the request to update the quantities of a specific food item in the inventory.
    * <p>
    * This method validates the provided food ID and quantity using {@link FoodQuantityValidator}. If validation
    * fails, it forwards the request back to the "addQuantities.jsp" page with an error message. If validation succeeds,
    * it calls the {@link RetailerService} to update the food quantities. Upon successful update, it redirects the user
    * to the "retailer.jsp" page with a success message. If any exceptions occur during the update process, the method
    * forwards the request back to the "addQuantities.jsp" page with the relevant error message.
    * </p>
    *
    * @param request the {@link HttpServletRequest} object that contains the request from the client
    * @param response the {@link HttpServletResponse} object that will be used to return the response to the client
    * @param retailer the {@link Retailer} object representing the logged-in retailer who is performing the update
    * @throws ServletException if an error occurs while processing the request or forwarding to another page
    * @throws IOException if an input or output error occurs while handling the request or forwarding the response
    */
    private void storeAddQuantity(HttpServletRequest request, HttpServletResponse response, Retailer retailer) throws ServletException, IOException{
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
    * Handles the request to display the page where the retailer can change the expiration days of food items.
    * <p>
    * This method retrieves a list of all food items using the {@link RetailerService}. If an exception occurs
    * during the retrieval process, it forwards the request to the "retailer.jsp" page with an error message. If
    * retrieval is successful, it sets the list of foods as an attribute in the request and forwards the request to
    * the "changeExpireDays.jsp" page where the retailer can modify the expiration days.
    * </p>
    *
    * @param request the {@link HttpServletRequest} object that contains the request from the client
    * @param response the {@link HttpServletResponse} object that will be used to return the response to the client
    * @throws ServletException if an error occurs while processing the request or forwarding to another page
    * @throws IOException if an input or output error occurs while handling the request or forwarding the response
    */
    private void changeFoodExpireDays(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RetailerService retailerService = new RetailerService();
        ArrayList<Food> foods = new ArrayList<>();
        try {
            foods = retailerService.getAllFoods();
        } catch (ClassNotFoundException | SQLException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/retailer.jsp");
            dispatcher.forward(request, response);
        } 
        
        request.setAttribute("foods", foods);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/changeExpireDays.jsp");
        dispatcher.forward(request, response);
    }

    /**
    * Handles the request to store updated expiration days for a specific food item.
    * <p>
    * This method extracts the food ID and the new expiration days from the request parameters. It then validates
    * the data using {@link FoodExpireDaysValidator}. If validation fails, it forwards the request to the "changeExpireDays.jsp"
    * page with an error message. If validation passes, it attempts to store the updated expiration days using
    * {@link RetailerService}. If an exception occurs during this process, it forwards the request to the same JSP
    * page with an error message. If successful, it redirects the user to the "retailer.jsp" page with a success message.
    * </p>
    *
    * @param request the {@link HttpServletRequest} object that contains the request from the client
    * @param response the {@link HttpServletResponse} object that will be used to return the response to the client
    * @throws ServletException if an error occurs while processing the request or forwarding to another page
    * @throws IOException if an input or output error occurs while handling the request or forwarding the response
    */
    private void storeExpireDays(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
    
    /**
    * Handles the request to display the page for updating inventory expiration dates.
    * <p>
    * This method retrieves a list of all expiration information items from the {@link RetailerService}. If successful,
    * it sets the retrieved data as an attribute on the request and forwards the request to the "updateExpireDate.jsp"
    * page for user interaction. If an exception occurs while retrieving the expiration information, it sets an error
    * message as a request attribute and forwards the request to the "retailer.jsp" page.
    * </p>
    *
    * @param request the {@link HttpServletRequest} object that contains the request from the client
    * @param response the {@link HttpServletResponse} object that will be used to return the response to the client
    * @throws ServletException if an error occurs while processing the request or forwarding to another page
    * @throws IOException if an input or output error occurs while handling the request or forwarding the response
    */
    private void updateInventoryExpireDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RetailerService retailerService = new RetailerService();
        ArrayList<ExpireInfo> expireInfos = new ArrayList<>();
        try {
            expireInfos = retailerService.getAllExpireInfoItems();
        } catch (ClassNotFoundException | SQLException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/retailer.jsp");
            dispatcher.forward(request, response);
        } 
        
        request.setAttribute("expireInfos", expireInfos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/updateExpireDate.jsp");
        dispatcher.forward(request, response);
    }

    /**
    * Handles the request to update the expiration dates of food items in the inventory.
    * <p>
    * This method processes the form submission for updating expiration dates. It extracts date values from the request parameters,
    * validates them, and updates the corresponding food items in the database. If the parameters are invalid or an error occurs,
    * it forwards the request to the "updateExpireDate.jsp" page with an error message. If successful, it redirects to the retailer page 
    * with a success message.
    * </p>
    *
    * @param request the {@link HttpServletRequest} object that contains the request from the client
    * @param response the {@link HttpServletResponse} object that will be used to return the response to the client
    * @throws ServletException if an error occurs while processing the request or forwarding to another page
    * @throws IOException if an input or output error occurs while handling the request or forwarding the response
    */
    private void storeUpdateExpireDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Map<String, String[]> parameterMap = request.getParameterMap();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        RetailerService retailerService = new RetailerService();
         
        // 获取之前存储的 expireInfos
        ArrayList<ExpireInfo> expireInfos = buildExpireInfoExpireDate(request);                
         
         // 处理 'action' 参数，确定请求的操作
         String action = request.getParameter("action");
         if ("storeUpdateExpireDate".equals(action)) {
            // 遍历参数映射
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                String paramName = entry.getKey();
                String[] paramValues = entry.getValue();
                Date newExpireDate = null;

                // 检查参数名称是否以 'newExpireDate_' 开头
                if (paramName.startsWith("newExpireDate_")) {
                    // 提取 'newExpireDate_' 后的 ID 部分
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
            // 转换为 double 类型并设置属性
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
    
    /**
    * Builds a list of {@link ExpireInfo} objects from the request parameters.
    * <p>
    * This method extracts various food and expiration-related data from the request parameters, constructs {@link Food} and
    * {@link ExpireInfo} objects, and populates a list with these objects. The method handles conversions and possible parsing errors,
    * setting default values when necessary.
    * </p>
    *
    * @param request the {@link HttpServletRequest} object that contains the request from the client
    * @return an {@link ArrayList} of {@link ExpireInfo} objects constructed from the request parameters
    */
    private void identifySurplus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RetailerService retailerService = new RetailerService();
        ArrayList<ExpireInfo> expireInfos = new ArrayList<>();
        try {
            expireInfos = retailerService.getExpireInfoClosedToExpireItems();
            
            request.setAttribute("expireInfos", expireInfos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/markSurplus.jsp");
            dispatcher.forward(request, response);
            return;
        } catch (ClassNotFoundException | SQLException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/retailer.jsp");
            dispatcher.forward(request, response);
            return;
        }         
    }
    
    private void storeUpdateIsSurplus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Map<String, String[]> parameterMap = request.getParameterMap();
        RetailerService retailerService = new RetailerService();
         
        // 获取之前存储的 expireInfos
        ArrayList<ExpireInfo> expireInfos = (ArrayList<ExpireInfo>) request.getAttribute("expireInfos");                         
         
         // 处理 'action' 参数，确定请求的操作
         String action = request.getParameter("action");
         if ("storeUpdateIsSurplus".equals(action)) {
            // 遍历参数映射
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                String paramName = entry.getKey();
                String[] paramValues = entry.getValue();
                boolean newIsSurplus;

                // 检查参数名称是否以 'newExpireDate_' 开头
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
    
    /**
    * Updates the surplus status of food items based on request parameters.
    * <p>
    * This method processes the request parameters to update the "is surplus" status of food items. It retrieves the previously stored
    * {@link ExpireInfo} objects from the request attributes and updates their surplus status. The method validates the new surplus status
    * and handles errors, forwarding to the appropriate JSP page if necessary.
    * </p>
    *
    * @param request the {@link HttpServletRequest} object that contains the request from the client
    * @param response the {@link HttpServletResponse} object that will be used to respond to the client
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
    private void listSurplus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RetailerService retailerService = new RetailerService();
        
        try {
            HashMap<Food, Integer[]> foodExpireQtyMap = retailerService.getFoodSurplusSummary();
            int count = foodExpireQtyMap.size();
            request.setAttribute("count", count);
            request.setAttribute("foodExpireQtyMap", foodExpireQtyMap);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/listSurplus.jsp");
            dispatcher.forward(request, response);
            return;
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/retailer.jsp");
            dispatcher.forward(request, response);
            return;
        }
    }
    
    /**
    * Handles the storage and processing of surplus and donation quantities for food items.
    * <p>
    * This method processes request parameters to update quantities of food items to be discounted or donated. It performs validations
    * and handles errors by forwarding to the appropriate JSP page if necessary. The method updates the quantities based on user input
    * and interacts with the service layer to apply the changes.
    * </p>
    *
    * @param request the {@link HttpServletRequest} object that contains the request from the client
    * @param response the {@link HttpServletResponse} object that will be used to respond to the client
    * @param retailer the {@link Retailer} object representing the retailer performing the actions
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
    private void storeListSurplus(HttpServletRequest request, HttpServletResponse response, Retailer retailer) throws ServletException, IOException{
        Map<String, String[]> parameterMap = request.getParameterMap();
        RetailerService retailerService = new RetailerService();
        SurplusValidator validator = new SurplusValidator();
        int count = 0;
         
        // 获取之前存储的 expireInfos
        HashMap<Food, Integer[]> foodExpireQtyMap = buildFoodExpireQtyMap(request);                
        
        HashMap<Integer, Integer[]> listDataToStore = new HashMap<>();
        HashMap<Integer, Integer[]> tempDataMap = new HashMap<>();
         
        // 处理 'action' 参数，确定请求的操作
        String action = request.getParameter("action");
        if ("storeListSurplus".equals(action)) {
           // 遍历参数映射
           for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
               String paramName = entry.getKey();
               String[] paramValues = entry.getValue();

               if (paramName.startsWith("qtyToDiscount_")) {
                   String idStr = paramName.substring("qtyToDiscount_".length());
                   try {
                       int id = Integer.parseInt(idStr);
                       int qtyToDiscount;
                       
                       if (paramValues[0] == null || paramValues[0].isEmpty()) {
                            qtyToDiscount = 0;
                        } else {
                            qtyToDiscount = Integer.parseInt(paramValues[0]);
                        }
                       
                       // 获取或创建临时数组
                       Integer[] qtyArray = tempDataMap.getOrDefault(id, new Integer[2]);
                       qtyArray[0] = qtyToDiscount;
                       tempDataMap.put(id, qtyArray);
                   } catch (NumberFormatException e) {
                        count = foodExpireQtyMap.size();
                        request.setAttribute("count", count);
                        request.setAttribute("foodExpireQtyMap", foodExpireQtyMap); // Set expireInfos
                        request.setAttribute("errorMessage", e.getMessage());
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/listSurplus.jsp");
                        dispatcher.forward(request, response);
                        return; 
                   }
               }
               // 检查参数名称是否以 'qtyToDonate_' 开头
               if (paramName.startsWith("qtyToDonate_")) {
                   String idStr = paramName.substring("qtyToDonate_".length());
                   try {
                       int id = Integer.parseInt(idStr);
                       int qtyToDonate;
                       
                       if (paramValues[0] == null || paramValues[0].isEmpty()) {
                            qtyToDonate = 0;
                        } else {
                            qtyToDonate = Integer.parseInt(paramValues[0]);
                        }

                       // 获取或创建临时数组
                       Integer[] qtyArray = tempDataMap.getOrDefault(id, new Integer[2]);
                       qtyArray[1] = qtyToDonate;
                       tempDataMap.put(id, qtyArray);
                   } catch (NumberFormatException e) {
                        count = foodExpireQtyMap.size();
                        request.setAttribute("count", count);
                        request.setAttribute("foodExpireQtyMap", foodExpireQtyMap); // Set expireInfos
                        request.setAttribute("errorMessage", e.getMessage());
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/listSurplus.jsp");
                        dispatcher.forward(request, response);
                        return; 
                   }
               }
               listDataToStore.putAll(tempDataMap);
           }
        }
        
        if(listDataToStore.isEmpty()){
            count = foodExpireQtyMap.size();
            request.setAttribute("count", count);
            request.setAttribute("foodExpireQtyMap", foodExpireQtyMap); // Set expireInfos
            request.setAttribute("errorMessage", "No item to be updated.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/listSurplus.jsp");
            dispatcher.forward(request, response);
            return; 
        }
        
        for (Map.Entry<Integer, Integer[]> entry : listDataToStore.entrySet()) {
            int foodId = entry.getKey();
            Integer[] qtyArray = entry.getValue();
            int qtyToDiscount = (qtyArray[0] != null) ? qtyArray[0] : 0;
            int qtyToDonate = (qtyArray[1] != null) ? qtyArray[1] : 0;

            if(!(qtyToDiscount == 0 && qtyToDiscount == 0) ){
                if (!validator.validate(foodId, qtyToDiscount, qtyToDonate, foodExpireQtyMap)) {
                    count = foodExpireQtyMap.size();
                    request.setAttribute("count", count);
                    request.setAttribute("foodExpireQtyMap", foodExpireQtyMap);
                    request.setAttribute("errorMessage", validator.getErrorMessage());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/listSurplus.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
                try {
                    retailerService.listSurplusFood(foodId, qtyToDiscount, qtyToDonate, retailer);
                    updateNotificationCount(request);
                } catch (NegativeInventoryException ex) {
                    request.setAttribute("errorMessage", "Quantity to deduct is greater than inventory.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/listSurplus.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException | ClassNotFoundException ex) {
                    request.setAttribute("errorMessage", ex.getMessage());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/listSurplus.jsp");
                    dispatcher.forward(request, response);
                } 
            }
            
        }

        // 重定向到成功页面或其他处理逻辑
        response.sendRedirect(request.getContextPath() + "/views/retailer.jsp?successMessage=Surplus%20quantities%20listed%20successfully.");
    }
    
    /**
    * Builds a mapping of {@link Food} objects to their corresponding quantities based on request parameters.
    * <p>
    * This method processes request parameters to create a map where each {@link Food} object is associated with an array of quantities. 
    * The quantities represent total surplus quantity, normal inventory quantity, quantities listed for discount, and quantities listed for donation.
    * </p>
    *
    * @param request the {@link HttpServletRequest} object that contains the request from the client
    * @return a {@link HashMap} where keys are {@link Food} objects and values are {@link Integer[]} arrays containing the quantities
    */
    private HashMap<Food, Integer[]> buildFoodExpireQtyMap(HttpServletRequest request) {
        String[] foodIds = request.getParameterValues("foodId");
        String[] foodNames = request.getParameterValues("foodName");
        String[] foodExpireDays = request.getParameterValues("foodExpireDays");
        String[] foodUnitprices = request.getParameterValues("foodUnitprice");
        String[] foodDiscounts = request.getParameterValues("foodDiscount");
        String[] totalSurplusQtys = request.getParameterValues("totalSurplusQty");
        String[] inventoryNormals = request.getParameterValues("inventoryNormal");
        String[] listedForDiscounts = request.getParameterValues("listedForDiscount");
        String[] listedForDonations = request.getParameterValues("listedForDonation");

        HashMap<Food, Integer[]> foodExpireQtyMap = new HashMap<>();
        for (int i = 0; i < foodIds.length; i++) {
            Food food = new Food();
            food.setId(Integer.parseInt(foodIds[i]));
            food.setName(foodNames[i]);
            food.setExpireDays(Integer.parseInt(foodExpireDays[i]));
            // 转换为 double 类型并设置属性
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

            Integer[] qtyArray = new Integer[4];
            qtyArray[0] = Integer.parseInt(totalSurplusQtys[i]);
            qtyArray[1] = Integer.parseInt(inventoryNormals[i]);
            qtyArray[2] = Integer.parseInt(listedForDiscounts[i]);
            qtyArray[3] = Integer.parseInt(listedForDonations[i]);
            foodExpireQtyMap.put(food, qtyArray);
        }

        return foodExpireQtyMap;
    }
    
    
    private void viewInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
    
    /**
    * Handles the request to view inventory data and forwards the response to the inventory view page.
    * <p>
    * This method retrieves the complete inventory data using the {@link RetailerService} and sets it as a request attribute 
    * for the JSP view. If an exception occurs during data retrieval, the method sets an error message and forwards the request 
    * to the retailer view page.
    * </p>
    *
    * @param request the {@link HttpServletRequest} object that contains the request from the client
    * @param response the {@link HttpServletResponse} object that will be used to send the response to the client
    * @throws ServletException if a servlet error occurs during request processing
    * @throws IOException if an I/O error occurs during request or response handling
    */
    private void updateNotificationCount(HttpServletRequest request) throws SQLException, ClassNotFoundException{
        HttpSession session = request.getSession(false);
        int count[] = new int[2];
        int phoneNotificationCount = 0;
        int emailNotificationCount = 0;
        UserService userService = new UserService();
        
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                count = userService.getNotificationCount(user);
                // 假设你有获取通知计数的方法
                emailNotificationCount = count[0];
                phoneNotificationCount = count[1];
                
                session.setAttribute("phoneNotificationCount", phoneNotificationCount);
                session.setAttribute("emailNotificationCount", emailNotificationCount);
            }
        }
       
     }
    
    /*
    private void viewTransactions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RetailerService retailerService = new RetailerService();
        
        ArrayList<Transaction> transactions = retailerService.getAllTransactions();
        int count = transactions.size();
        request.setAttribute("count", count);
        request.setAttribute("transactions", transactions);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/transactions.jsp");
        dispatcher.forward(request, response);
    }
    */
}
