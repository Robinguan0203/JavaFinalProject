/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.usercommand;

import com.fwrp.constants.NotificationMethodConstant;


/**
 *
 * @author robin
 */
public class UserCommandFactory {
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
            case "viewEmailPhoneNotification":
                return new ViewNotificationCommand(NotificationMethodConstant.EMAIL);
            default:
                return new UnknownCommand(); 
        }
    }
}