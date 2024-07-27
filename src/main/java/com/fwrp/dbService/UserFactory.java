/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dbService;

import com.fwrp.constants.UserTypeConstant;

import com.fwrp.models.User;

/**
 *
 * @author robin
 */
public class UserFactory {
    public static User createUser(int type){
        User user = null;
        switch(type){
            case UserTypeConstant.RETAILER:
                RetailerBuilder retailerBuilder = RetailerBuilder.create();
                user = retailerBuilder.build();
                break;
            case UserTypeConstant.CONSUMER:
                ConsumerBuilder consumerBuilder = ConsumerBuilder.create();
                user = consumerBuilder.build();
                break;
            case UserTypeConstant.CHARITY:
                CharityBuilder charityBuilder = CharityBuilder.create();
                user = charityBuilder.build();
                break;
            default:
                throw new IllegalArgumentException("Invalid user type: " + type);
        }
        
        return user;
    }
}
