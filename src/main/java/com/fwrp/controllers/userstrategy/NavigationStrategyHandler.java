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
 * Handler for navigation strategies.
 * 
 * This class manages the navigation strategy and delegates the 
 * navigation to the set strategy.
 * 
 * Author: Robin Guan
 * Version: 1.0
 * Since: 17.0.8
 */
public class NavigationStrategyHandler {
	
	/**
     * The current navigation strategy.
     */
    private INavigationStrategy strategy = new DefaultNavigationStrategy();
    
	/**
     * Sets the navigation strategy.
     * 
     * @param strategy The navigation strategy to set.
     */
    public void setStragety(INavigationStrategy strategy){
        this.strategy = strategy;
    }
    
	/**
     * Navigates using the current strategy.
     * 
     * @param request  The HttpServletRequest object that contains the request the client made to the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    public void navigate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        strategy.navigate(request, response);
    }
}
