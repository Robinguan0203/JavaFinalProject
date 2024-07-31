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
public class NavigationStrategySelector {
    private NavigationStrategyHandler handler;
    
    public NavigationStrategySelector(){
        this.handler = new NavigationStrategyHandler();
    }
    
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
            default:
                handler.setStragety(new DefaultNavigationStrategy());
        }
    }
    
    public void navigate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        handler.navigate(request, response);
    }
}
