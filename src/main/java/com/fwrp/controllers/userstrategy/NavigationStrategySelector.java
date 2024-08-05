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
 * Selector for navigation strategies.
 * 
 * This class selects and sets the appropriate navigation strategy 
 * based on the provided action.
 * 
 * Author: Robin Guan
 * Version: 1.0
 * Since: 17.0.8
 */
public class NavigationStrategySelector {
	
	/**
     * The handler that manages the navigation strategy.
     */
    private NavigationStrategyHandler handler;
    
	/**
     * Constructs a NavigationStrategySelector and initializes the handler.
     */
    public NavigationStrategySelector(){
        this.handler = new NavigationStrategyHandler();
    }
    
	/**
     * Selects the navigation strategy based on the provided action.
     * 
     * @param action The action to determine the navigation strategy.
     */
    public void selectNavigationStrategy(String action){
        switch(action){
            case "viewRetailer":
                handler.setStragety(new ViewRetailerStrategy());
                break;
            case "viewCharity":
                handler.setStragety(new ViewCharityStrategy());
                break;
            case "viewConsumer":
                handler.setStragety(new ViewConsumerStrategy());
                break;
            case "preference":
                handler.setStragety(new ViewConsumerStrategy());
                break;
            default:
                handler.setStragety(new DefaultNavigationStrategy());
        }
    }
    
	/**
     * Navigates using the selected strategy.
     * 
     * @param request  The HttpServletRequest object that contains the request the client made to the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    public void navigate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        handler.navigate(request, response);
    }
}
