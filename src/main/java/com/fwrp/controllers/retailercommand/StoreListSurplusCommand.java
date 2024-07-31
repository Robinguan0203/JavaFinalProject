/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.retailercommand;

import com.fwrp.exceptions.NegativeInventoryException;
import com.fwrp.models.Food;
import com.fwrp.models.Retailer;
import com.fwrp.models.User;
import com.fwrp.services.RetailerService;
import com.fwrp.services.UserService;
import com.fwrp.validator.SurplusValidator;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author robin
 */
public class StoreListSurplusCommand  implements IRetailerCommand{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        RetailerService retailerService = new RetailerService();
        SurplusValidator validator = new SurplusValidator();
        int count = 0;
         
        // 获取之前存储的 expireInfos
        HashMap<Food, Integer[]> foodExpireQtyMap = buildFoodExpireQtyMap(request);                
        
        HashMap<Integer, Integer[]> listDataToStore = new HashMap<>();
        HashMap<Integer, Integer[]> tempDataMap = new HashMap<>();
        Retailer retailer = this.getRetailerFromSession(request);
         
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
}
