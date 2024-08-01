/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.services;

import com.fwrp.dataaccess.dto.NotificationDTO;
import com.fwrp.dataaccess.dto.UserDTO;
import com.fwrp.dbService.UserDbService;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.exceptions.DataNotExistsException;
import com.fwrp.models.Notification;
import com.fwrp.models.User;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Service class for managing user-related operations.
 * This class provides methods for user authentication, registration, and notification retrieval.
 * 
 * <p>
 * Example use case: Use this service to authenticate users, register new accounts, and fetch notifications for users.
 * </p>
 * 
 * @version 1.0
 * @since 2023-07-30
 * 
 */
public class UserService {
    
    /**
     * Retrieves a user based on email and password credentials.
     * 
     * @param email the email address of the user
     * @param password the password of the user
     * @return the User object corresponding to the provided credentials
     * @throws DataNotExistsException if no user exists with the provided credentials
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the database driver class cannot be found
     */
    public User getUserByCredentials(String email, String password) throws DataNotExistsException, SQLException, ClassNotFoundException{
        UserDbService userDbService = new UserDbService();
        User user = userDbService.getLoginUser(email, password);
        
        return user;
    }
    
     /**
     * Registers a new user with the provided details.
     * 
     * @param firstname the first name of the user
     * @param lastname the last name of the user
     * @param phone the phone number of the user
     * @param email the email address of the user
     * @param password the password for the user account
     * @param type the type of user (e.g., Retailer, Consumer, Charity)
     * @param organization the organization name (relevant for certain user types)
     * @throws DataAlreadyExistsException if a user with the same email already exists
     * @throws DataInsertionFailedException if there is an error inserting the user into the database
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the database driver class cannot be found
     */
    public void register(String firstname, String lastname, String phone, String email, String password, String type, String organization) throws DataAlreadyExistsException, DataInsertionFailedException, SQLException, ClassNotFoundException{
        UserDbService userDbService = new UserDbService();
        UserDTO userToAddDTO = new UserDTO();
        userToAddDTO.setFirstName(firstname);
        userToAddDTO.setLastName(lastname);
        userToAddDTO.setPhone(phone);
        userToAddDTO.setEmail(email);
        userToAddDTO.setPassword(password);
        userToAddDTO.setType(Integer.parseInt(type));
        userToAddDTO.setOrganization(organization);
        
        userDbService.AddUser(userToAddDTO);
    }
    
    /**
     * Retrieves notifications for a user based on the specified method.
     * 
     * @param user the User object for whom notifications are to be retrieved
     * @param method the method to filter notifications (e.g., by email, SMS)
     * @return a list of Notification objects for the user
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the database driver class cannot be found
     */
    public ArrayList<Notification> getNotifications(User user, int method) throws SQLException, ClassNotFoundException{
        UserDbService userDbService = new UserDbService();
        ArrayList<Notification> notifications = new ArrayList<>();
        ArrayList<NotificationDTO> notificationDTOs = userDbService.getNotificationsByMethod(user, method);
        
        if(!notificationDTOs.isEmpty()){
            for(NotificationDTO dto : notificationDTOs){
                notifications.add(dto.transferToNotification());
            }
        }
        
        return notifications;
    }
    
    /**
     * Retrieves the notification count for a user.
     * 
     * @param user the User object for whom notification counts are to be retrieved
     * @return an array containing the count of notifications
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the database driver class cannot be found
     */
    public int[] getNotificationCount(User user) throws SQLException, ClassNotFoundException {
        int[] count = new int[3];
        UserDbService userDbService = new UserDbService();
        
        count = userDbService.getNotificationCountByUser(user);
        
        return count;
    }

}
