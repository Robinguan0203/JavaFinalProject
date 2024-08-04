/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.userstrategy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for navigation strategies.
 * 
 * This interface defines a method for navigating based on the 
 * provided HttpServletRequest and HttpServletResponse objects.
 * 
 * Author: Robin Guan
 * Version: 1.0
 * Since: 17.0.8
 */
public interface INavigationStrategy {
	
	/**
     * Navigates based on the provided request and response.
     * 
     * @param request  The HttpServletRequest object that contains the request the client made to the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    void navigate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
