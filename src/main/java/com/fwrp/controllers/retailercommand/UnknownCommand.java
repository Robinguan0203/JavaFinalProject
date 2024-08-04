/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.retailercommand;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to handle unknown actions for a retailer.
 * 
 * This class implements the IRetailerCommand interface and handles the 
 * execution of unknown actions by responding with a simple message.
 *  
 * Author: Robin Guan
 * Version: 1.0
 * Since: 17.0.8
 */
public class UnknownCommand  implements IRetailerCommand{

	/**
     * Executes the command to handle unknown actions.
     * 
     * @param request  The HttpServletRequest object that contains the request the client made to the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("Unknown action");
    }
}