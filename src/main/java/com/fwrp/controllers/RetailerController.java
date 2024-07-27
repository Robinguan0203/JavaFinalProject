/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers;

import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.exceptions.DateNotExistsException;
import com.fwrp.exceptions.NegativeInventoryException;
import com.fwrp.models.ExpireInfo;
import com.fwrp.models.Food;
import com.fwrp.models.Retailer;
import com.fwrp.services.RetailerService;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet("/retailerController")
public class RetailerController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Retailer retailer = this.getRetailerFromSession(request);
        request.setAttribute("retailer", retailer);
        
        String action = request.getParameter("action");
        switch (action) {
            case "addFood":
                addFood(request, response);
                break;
            case "storeNewFood":
                storeNewFood(request,response);
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
               // identifySurplus(request, response);
                break;
            case "listSurplus":
                //listSurplus(request, response);
                break;
            default:
                response.getWriter().println("Unknown action");
        }
    }
    
    private Retailer getRetailerFromSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Retailer) session.getAttribute("user");
        }
        return null;
    }
    
    
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
        
        RetailerService retailerService = new RetailerService();
        try {
            retailerService.storeNewFood(name, expireDays, unitPrice, discount);
            response.sendRedirect(request.getContextPath() + "/views/retailer.jsp?successMessage=Food%20added%20successfully.");
        } catch(DataAlreadyExistsException ee){
            request.setAttribute("errorMessage", "Food Already Exist.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/add.jsp");
            dispatcher.forward(request, response);
        } catch (DataInsertionFailedException ex) {
            request.setAttribute("errorMessage", "Food Insertion Failed.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/add.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e){
            request.setAttribute("errorMessage", "An unexpected error occurred.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/add.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private void addQuantities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RetailerService retailerService = new RetailerService();
        ArrayList<Food> foods = new ArrayList<>();
        try {
            foods = retailerService.getAllFoods();
        } catch (ClassNotFoundException | SQLException ex) {
            request.setAttribute("errorMessage", "An unexpected error occurred.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/retailer.jsp");
            dispatcher.forward(request, response);
        } 
        
        request.setAttribute("foods", foods);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/addQuantities.jsp");
        dispatcher.forward(request, response);
    }
    
    private void storeAddQuantity(HttpServletRequest request, HttpServletResponse response, Retailer retailer) throws ServletException, IOException{
        int foodId = Integer.parseInt(request.getParameter("foodId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        RetailerService retailerService = new RetailerService();
        try {
            retailerService.addFoodQuantities(foodId, quantity, retailer);
            response.sendRedirect(request.getContextPath() + "/views/retailer.jsp?successMessage=Food%20quantity%20updated%20successfully.");
        } catch (NegativeInventoryException ex) {
            request.setAttribute("errorMessage", "Quantity to deduct is greater than inventory.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/addQuantities.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("errorMessage", "An unexpected error occurred.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/addQuantities.jsp");
            dispatcher.forward(request, response);
        }  
    }
    
    private void changeFoodExpireDays(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RetailerService retailerService = new RetailerService();
        ArrayList<Food> foods = new ArrayList<>();
        try {
            foods = retailerService.getAllFoods();
        } catch (ClassNotFoundException | SQLException ex) {
            request.setAttribute("errorMessage", "An unexpected error occurred.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/retailer.jsp");
            dispatcher.forward(request, response);
        } 
        
        request.setAttribute("foods", foods);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/changeExpireDays.jsp");
        dispatcher.forward(request, response);
    }

    private void storeExpireDays(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int foodId = Integer.parseInt(request.getParameter("foodId"));
        int expireDays = Integer.parseInt(request.getParameter("expireDays"));
        
        RetailerService retailerService = new RetailerService();
        try {
            retailerService.storeFoodExpireDays(foodId, expireDays);
            response.sendRedirect(request.getContextPath() + "/views/retailer.jsp?successMessage=Food%20expire%20days%20updated%20successfully.");
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/changeExpireDays.jsp");
            dispatcher.forward(request, response);
        } catch (DataInsertionFailedException ex) {
            request.setAttribute("errorMessage", "Store new expire days fails.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/changeExpireDays.jsp");
            dispatcher.forward(request, response);
        }  
    }
    
    private void updateInventoryExpireDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RetailerService retailerService = new RetailerService();
        ArrayList<ExpireInfo> expireInfos = new ArrayList<>();
        try {
            expireInfos = retailerService.getAllExpireInfoItems();
        } catch (ClassNotFoundException | SQLException ex) {
            request.setAttribute("errorMessage", "An unexpected error occurred.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/retailer.jsp");
            dispatcher.forward(request, response);
        } 
        
        request.setAttribute("expireInfos", expireInfos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/updateExpireDate.jsp");
        dispatcher.forward(request, response);
    }

    private void storeUpdateExpireDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         Map<String, String[]> parameterMap = request.getParameterMap();
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         RetailerService retailerService = new RetailerService();
                         
         
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
                    try {
                        int id = Integer.parseInt(idStr);
                        // 处理 'newExpireDate' 参数的值
                        String newExpireDateStr  = paramValues[0]; // 参数值数组的第一个值
                        try {
                            newExpireDate = dateFormat.parse(newExpireDateStr);
                        } catch (ParseException ex) {
                            request.setAttribute("errorMessage", "Wrong Date Format.");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/updateExpireDate.jsp");
                            dispatcher.forward(request, response);
                        }

                        retailerService.updateExpireDateOfExpireInfo(id, newExpireDate);

                    } catch (NumberFormatException e) {
                        request.setAttribute("errorMessage", "Wrong Number format.");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/updateExpireDate.jsp");
                        dispatcher.forward(request, response);
                    } catch (SQLException | ClassNotFoundException ex) {
                        request.setAttribute("errorMessage", "WAn unexpected error occurred.");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/updateExpireDate.jsp");
                        dispatcher.forward(request, response);
                    } catch (DateNotExistsException ex) {
                        request.setAttribute("errorMessage", ex.getMessage());
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/updateExpireDate.jsp");
                        dispatcher.forward(request, response);
                    } 
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/views/retailer.jsp?successMessage=Food%20expire%20dates%20updated%20successfully.");
    }
}
