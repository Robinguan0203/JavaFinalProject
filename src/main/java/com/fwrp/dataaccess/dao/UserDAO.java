package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.UserDTO;
import com.fwrp.models.User;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This interface defines the standard operations to be performed on User model object(s).
 * It provides methods to add, retrieve, and remove user from a data source.
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public interface UserDAO {
    /**
     * Adds a new user to the data source.
     * 
     * @param userDTO The user object to be added.
     * @param conn SQL connection
     * @return Boolean Returns true if the user was successfully added, false otherwise.
     * @throws java.sql.SQLException
     */
    boolean addUser(UserDTO userDTO, Connection conn)  throws SQLException;
    
    /**
     * Retrieves a user by its ID.
     * 
     * @param userId The ID of the user to retrieve.
     * @param conn SQL connection
     * @return User Returns the user object if found, null otherwise.
     * @throws java.sql.SQLException
     */
    UserDTO getUserById(int userId, Connection conn)  throws SQLException;
    
    /**
     * Retrieves a user by its email.
     * 
     * @param email The email of the user to retrieve.
     * @param conn SQL Connection
     * @return User Returns the user object if found, null otherwise.
     * @throws java.sql.SQLException
     */
    UserDTO getUserByEmail(String email, Connection conn)  throws SQLException;
    
    /**
     * Retrieves a user by its email and password.
     * 
     * @param email The email of the user to retrieve.
     * @param password The password of the user to retrieve.
     * @param conn SQL Connection
     * @return User Returns the user object if found, null otherwise.
     * @throws java.sql.SQLException
     */
    UserDTO getUserByEmailAndPassword(String email, String password, Connection conn)  throws SQLException;
    
    /**
     * Update a user.
     * 
     * @param userDTO The new user data to save.
     * @param conn SQL Connection
     * @return Boolean Returns true if the user was successfully updated, false otherwise.
     * @throws java.sql.SQLException
     */
    boolean updateUser(UserDTO userDTO, Connection conn)  throws SQLException;
    
    /**
     * Removes a user from the data source.
     * 
     * @param userDTO The UserDTO object of the user to be removed.
     * @param conn SQL Connection
     * @return Boolean Returns true if the course was successfully removed, false otherwise.
     * @throws java.sql.SQLException
     */
    boolean removeUser(UserDTO userDTO, Connection conn)  throws SQLException;
    
}
