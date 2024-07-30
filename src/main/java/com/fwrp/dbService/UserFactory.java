/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dbService;

import com.fwrp.constants.UserTypeConstant;

import com.fwrp.models.User;

/**
 * A factory class for creating {@link User} objects based on user type.
 * <p>
 * This class uses the Simple Factory design pattern to create instances of different types
 * of {@link User} (Retailer, Consumer, Charity) based on the provided user type.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 1.0
 */
public class UserFactory {
    /**
     * Creates a {@link User} instance based on the specified user type.
     * <p>
     * This method uses different builders to create instances of {@link User} based on
     * the provided user type. The valid user types are defined in {@link UserTypeConstant}.
     * </p>
     * 
     * @param type The type of user to create. It should be one of the constants defined in {@link UserTypeConstant}.
     *             Possible values include:
     *             <ul>
     *                 <li>{@link UserTypeConstant#RETAILER}</li>
     *                 <li>{@link UserTypeConstant#CONSUMER}</li>
     *                 <li>{@link UserTypeConstant#CHARITY}</li>
     *             </ul>
     * @return A {@link User} object of the specified type.
     * @throws IllegalArgumentException If the provided user type is not valid.
     */
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
