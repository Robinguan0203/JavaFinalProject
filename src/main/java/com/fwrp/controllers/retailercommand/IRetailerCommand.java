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
 * This interface defines the command pattern for retailer operations.
 * <p>
 * Implementing classes should provide the logic for executing specific retailer commands.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public interface IRetailerCommand {
		
    /**
     * Executes the command.
     * <p>
     * This method should be implemented to handle the specific logic for the command, using the provided request and response objects.
     * </p>
     * 
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     */
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
