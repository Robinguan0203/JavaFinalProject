/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers;

import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.models.Retailer;
import com.fwrp.services.RetailerService;
import java.io.IOException;
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
                //addQuantities(request,response);
                break;
            case "setFoodExpireDays":
                //setFoodExpireDays(request, response);
                break;
            case "setInventoryExpireDate":
                //setInventoryExpireDate(request, response);
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
    
    /*
    private void addQuantities(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/addQuantities.jsp");
        dispatcher.forward(request, response);
        response.getWriter().println("Add Food functionality");
    }
*/
}
