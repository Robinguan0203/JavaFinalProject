/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.userstrategy;

/**
 *
 * @author robin
 */
public class NavigationStrategyFactory {
    public static INavigationStrategy getStrategy(String action) {
        switch (action) {
            case "viewRetailer":
                return new ViewRetailerStrategy();
            case "viewCharity":
                return new ViewCharityStrategy();
            case "viewConsumer":
                return new ViewConsumerStrategy();
            default:
                return new DefaultNavigationStrategy();
        }
    }
}
