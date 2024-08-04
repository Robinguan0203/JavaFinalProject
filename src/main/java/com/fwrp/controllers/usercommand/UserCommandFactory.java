/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.usercommand;

import com.fwrp.constants.NotificationMethodConstant;


/**
 * Factory class to create user commands based on action strings.
 * 
 * This class provides a static method to return an instance of 
 * IUserCommand based on the provided action string.
 * 
 * 
 * Author: Robin Guan
 * Version: 1.0
 * Since: 17.0.8
 */
public class UserCommandFactory {
	
	/**
     * Returns an instance of IUserCommand based on the provided action string.
     * 
     * @param action The action string that determines which command to create.
     * @return An instance of IUserCommand corresponding to the action.
     */
    public static IUserCommand getCommand(String action) {
        switch (action) {
            case "login":
                return new LoginCommand();
            case "openRegisterPage":
                return new OpenRegisterPageCommand();
            case "register":
                return new RegisterCommand();
            case "logout":
                return new LogoutCommand();
            case "viewPhoneNotification":
                return new ViewNotificationCommand(NotificationMethodConstant.PHONE);
            case "viewEmailNotification":
                return new ViewNotificationCommand(NotificationMethodConstant.EMAIL);
            case "viewSystemNotification":
                return new ViewNotificationCommand(NotificationMethodConstant.SYSTEM);
            case "manageSubscription":
                return new ManageSubscriptionCommand();
            case "showAddSubscription":
                return new ShowAddSubscriptionCommand();
            case "addSubscription":
                return new AddSubscriptionCommand();
            case "deleteSubscription":
                return new DeleteSubscriptionCommand();
            default:
                return new UnknownCommand(); 
        }
    }
}
