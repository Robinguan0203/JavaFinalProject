/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.userstrategy;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Navigation strategy for viewing the retailer page.
 * 
 * This class implements the INavigationStrategy interface and handles 
 * the navigation by forwarding the request to the "views/retailer.jsp" page.
 * 
 * Author: Robin Guan
 * Version: 1.0
 * Since: 17.0.8
 */
public class ViewRetailerStrategy implements INavigationStrategy {
    
	/**
     * Navigates to the retailer page.
     * 
     * @param request  The HttpServletRequest object that contains the request the client made to the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    @Override
    public void navigate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/retailer.jsp");
        dispatcher.forward(request, response);
    }
}
