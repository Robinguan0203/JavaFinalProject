/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.retailercommand;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class implements the command to add food in the retailer system.
 * <p>
 * It forwards the request to the add food view and prints a message indicating the add food functionality.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public class AddFoodCommand implements IRetailerCommand{

	/**
     * Executes the add food command.
     * <p>
     * This method forwards the request to the add food JSP view and prints a message to the response writer.
     * </p>
     * 
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/food/add.jsp");
        dispatcher.forward(request, response);
        response.getWriter().println("Add Food functionality");
    }
}
