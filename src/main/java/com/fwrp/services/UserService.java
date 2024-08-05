/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.services;

import com.fwrp.dataaccess.dto.NotificationDTO;
import com.fwrp.dataaccess.dto.SubscriptionDTO;
import com.fwrp.dataaccess.dto.PreferenceDTO;
import com.fwrp.dataaccess.dto.UserDTO;
import com.fwrp.dbService.SubscriptionDbService;
import com.fwrp.dbService.PreferenceDbService;
import com.fwrp.dbService.UserDbService;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.exceptions.DataNotExistsException;
import com.fwrp.models.Notification;
import com.fwrp.models.Preference;
import com.fwrp.models.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	 * Retrieves the notification count for a given user.
	 * 
	 * @param user The user for whom the notification count is to be retrieved.
	 * @return int[] An array containing the notification counts.
	 * @throws SQLException if a database access error occurs or the SQL query fails.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */
    public int[] getNotificationCountByUser(User user) throws SQLException, ClassNotFoundException {
        UserDbService userDbService = new UserDbService();
        return userDbService.getNotificationCount(user);
    }

    /**
     * Adds a subscription for a user with a specified method.
     * 
     * @param userId The ID of the user.
     * @param method The subscription method.
     * @throws Exception if an error occurs while adding the subscription.
     */
    public void addSubscription(int userId,int method) throws Exception{
        SubscriptionDbService dbService = new SubscriptionDbService();
        dbService.addSubscription(userId,method);
    }
    
    /**
     * Adds a preference for a user with a specified food ID.
     * 
     * @param userId The ID of the user.
     * @param foodId The ID of the food.
     * @throws Exception if an error occurs while adding the preference.
     */
    public void addPreference(int userId,int foodId) throws Exception{
        PreferenceDbService dbService = new PreferenceDbService();
        dbService.addPreference(userId,foodId);
    }

    /**
     * Retrieves all subscription methods for a given user ID.
     * 
     * @param userId The ID of the user.
     * @return {@code List<SubscriptionDTO>} A list of subscription methods associated with the specified user ID.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     */
    public List<SubscriptionDTO> getAllMethodsByUserId(int userId) throws ClassNotFoundException, SQLException {
        SubscriptionDbService dbService = new SubscriptionDbService();
        return dbService.getAllMethodsByUserId(userId);
    }
    
    /**
     * Retrieves all subscription methods for a given user ID.
     * 
     * @param userId The ID of the user.
     * @return {@code List<SubscriptionDTO>} A list of subscription methods associated with the specified user ID.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     */
    public List<Preference> getAllFoodIdByUserId(int userId) throws ClassNotFoundException, SQLException {
        PreferenceDbService dbService = new PreferenceDbService();
        return dbService.getAllFoodIdByUserId(userId);
    }

    /**
     * Deletes a subscription by its ID.
     * 
     * @param id The ID of the subscription to be deleted.
     * @throws Exception if an error occurs while deleting the subscription.
     */
    public void deleteSubscription(int id) throws Exception{
        SubscriptionDbService dbService = new SubscriptionDbService();
        dbService.deleteSubscription(id);
    }
    
    /**
     * Deletes a preference by its ID.
     * 
     * @param id The ID of the preference to be deleted.
     * @throws Exception if an error occurs while deleting the preference.
     */
    public void deletePreference(int id) throws Exception{
        PreferenceDbService dbService = new PreferenceDbService();
        dbService.deletePreference(id);
    }
}
