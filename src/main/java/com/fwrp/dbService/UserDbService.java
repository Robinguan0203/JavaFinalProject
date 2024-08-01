/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dbService;

import com.fwrp.constants.NotificationMethodConstant;
import com.fwrp.dataaccess.DataSource;
import com.fwrp.dataaccess.dao.NotificationDAO;
import com.fwrp.dataaccess.dao.NotificationDAOImpl;
import com.fwrp.dataaccess.dao.UserDAO;
import com.fwrp.dataaccess.dao.UserDAOImpl;
import com.fwrp.dataaccess.dto.NotificationDTO;
import com.fwrp.dataaccess.dto.UserDTO;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.exceptions.DataNotExistsException;
import com.fwrp.models.Notification;
import com.fwrp.models.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods for performing database operations related to users and notifications.
 * <p>
 * This class handles user addition, retrieval, and notification management using Data Access Objects (DAOs).
 * It ensures proper resource management and exception handling for database interactions.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 1.0
 */
public class UserDbService {
    
     /**
     * The Data Access Object (DAO) for performing user-related database operations.
     * <p>
     * This DAO provides methods to interact with the user database, including adding, retrieving,
     * and checking the existence of users.
     * </p>
     */
    private UserDAO userDAO = null;
    
    private List<NotificationCountObserver> observers = new ArrayList<>();
    
    /**
     * The Data Access Object (DAO) for performing notification-related database operations.
     * <p>
     * This DAO provides methods to interact with the notification database, including retrieving
     * notifications based on user ID and notification method.
     * </p>
     */
    private NotificationDAO notificationDAO = null;
    
    /**
     * Constructs a {@code UserDbService} object and initializes DAO objects for user and notification operations.
     */
    public UserDbService(){
        userDAO = new UserDAOImpl();   
        notificationDAO = new NotificationDAOImpl();  
        observers.add(new EmailNotificationCountObserver());
        observers.add(new PhoneNotificationCountObserver());
        observers.add(new SystemNotificationCountObserver());
    }
    
    /**
     * Adds a new user to the database.
     * <p>
     * Checks if a user with the specified email already exists. If not, adds the user.
     * Throws an exception if the user already exists or if there is a failure in adding the user.
     * </p>
     * 
     * @param userToAddDTO the {@link UserDTO} object containing user details to be added.
     * @return {@code true} if the user was successfully added; {@code false} otherwise.
     * @throws DataAlreadyExistsException if a user with the specified email already exists.
     * @throws DataInsertionFailedException if there is a failure in adding the user.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class cannot be found.
     */
    public boolean AddUser(UserDTO userToAddDTO) throws DataAlreadyExistsException, DataInsertionFailedException, SQLException, ClassNotFoundException {
        Connection conn = null;
        boolean isUserAdded = false;
        //UserDTO userToAddDTO = user.transferToUserDTO();
        try{
            conn = DataSource.getInstance().getConnection();
            UserDTO userExistDTO = userDAO.getUserByEmail(userToAddDTO.getEmail(), conn);
            if (userExistDTO != null) {
                throw new DataAlreadyExistsException("User with email " + userToAddDTO.getEmail() + " already exists.");
            }
            isUserAdded = userDAO.addUser(userToAddDTO, conn);
            if (!isUserAdded) {
                throw new DataInsertionFailedException("Failed to add user with email " + userToAddDTO.getEmail() + ".");
            }
        } catch(SQLException e){
            throw e;
        } finally{
            if (conn != null) {
                try {
                    conn.close(); // 关闭连接
                } catch (SQLException e) {
                    throw e; // Log the exception if closing connection fails
                }
            }
        }
               
        return isUserAdded;
    }
    
     /**
     * Retrieves a user by email and password.
     * <p>
     * Retrieves the user details based on the provided email and password. If found, returns a {@link User} object.
     * </p>
     * 
     * @param email the email of the user.
     * @param password the password of the user.
     * @return a {@link User} object if the user is found; {@code null} otherwise.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class cannot be found.
     */
    public User getLoginUser(String email, String password)throws SQLException, ClassNotFoundException{
        Connection conn = null;
        User user = null;
        int type;
        
        try{
            conn = DataSource.getInstance().getConnection();
            UserDTO userDTO = userDAO.getUserByEmailAndPassword(email, password, conn);
            
            if(userDTO == null){
                user = null;
            }else{
                type = userDTO.getType();
                user = UserFactory.createUser(type);
                user = userDTO.transferToUser(user);
            }
        }catch(SQLException e){
            throw e;
        } finally{
            if (conn != null) {
                try {
                    conn.close(); // 关闭连接
                } catch (SQLException e) {
                    throw e; // Log the exception if closing connection fails
                }
            }
        }
        
        return user;
    }
    
    /**
     * Retrieves a user by their ID.
     * <p>
     * Retrieves the user details based on the provided user ID. If found, returns a {@link User} object.
     * </p>
     * 
     * @param userId the ID of the user to retrieve.
     * @return a {@link User} object if the user is found; {@code null} otherwise.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class cannot be found.
     */
    public User getUserById(int userId)throws SQLException, ClassNotFoundException{
        Connection conn = null;
        User user = null;
        int type;
        
        try{
            conn = DataSource.getInstance().getConnection();
            UserDTO userDTO = userDAO.getUserById(userId, conn);
            
            if(userDTO == null){
                user = null;
            }else{
                type = userDTO.getType();
                user = UserFactory.createUser(type);
                user = userDTO.transferToUser(user);
            }
        }catch(SQLException e){
            throw e;
        } finally{
            if (conn != null) {
                try {
                    conn.close(); // 关闭连接
                } catch (SQLException e) {
                    throw e; // Log the exception if closing connection fails
                }
            }
        }
        
        return user;
    }
    
    /**
     * Retrieves notifications for a user by the specified notification method.
     * 
     * @param user the {@link User} for whom notifications are to be retrieved.
     * @param method the notification method (e.g., email or phone).
     * @return an {@link ArrayList} of {@link NotificationDTO} objects.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class cannot be found.
     */
    public ArrayList<NotificationDTO> getNotificationsByMethod(User user, int method) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        ArrayList<NotificationDTO> notificationDTOs = new ArrayList<>();
        
        try{
            conn = DataSource.getInstance().getConnection();
            notificationDTOs = notificationDAO.getNotificationByUserIdAndMethod(user.getId(), method, conn);
            
        } catch(SQLException e){
            throw e;
        } finally{
            if (conn != null) {
                try {
                    conn.close(); // 关闭连接
                } catch (SQLException e) {
                    throw e;
                }
            }
        }
     
        return notificationDTOs;
    }
    
    /**
     * Retrieves the count of notifications for a user by different methods (e.g., email and phone).
     * 
     * @param user the {@link User} for whom notification counts are to be retrieved.
     * @return an array of integers where the first element is the count of email notifications and
     *         the second element is the count of phone notifications.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class cannot be found.
     */
    public int[] getNotificationCountByUser(User user) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        int[] count = new int[3];
        
        try{
            conn = DataSource.getInstance().getConnection();
            count[0] = notificationDAO.getNotifictionCountByUserIdAndMethod(user.getId(), NotificationMethodConstant.EMAIL, conn);
            count[1] = notificationDAO.getNotifictionCountByUserIdAndMethod(user.getId(), NotificationMethodConstant.PHONE, conn);
            count[2] = notificationDAO.getNotifictionCountByUserIdAndMethod(user.getId(), NotificationMethodConstant.SYSTEM, conn);
        }catch(SQLException e){
            throw e;
        } finally{
            if (conn != null) {
                try {
                    conn.close(); // 关闭连接
                } catch (SQLException e) {
                    throw e;
                }
            }
        }
     
        return count;
        
    }
    
    public int[] getNotificationCount(User user) throws SQLException, ClassNotFoundException {
        int[] count = new int[observers.size()];
        for (int i = 0; i < observers.size(); i++) {
            count[i] = observers.get(i).getNotificationCount(user);
        }
        return count;
    }
}
