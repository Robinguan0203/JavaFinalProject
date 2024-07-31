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
 *
 * @author robin
 */
public class NavigationStrategyHandler {
    private INavigationStrategy strategy = new DefaultNavigationStrategy();
    
    public void setStragety(INavigationStrategy strategy){
        this.strategy = strategy;
    }
    
    public void navigate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        strategy.navigate(request, response);
    }
}
