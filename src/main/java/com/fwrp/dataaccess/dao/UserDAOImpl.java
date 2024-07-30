/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class implements the {@link UserDAO} interface to provide a concrete implementation for user data access.
 * It provides methods to add, retrieve, update, and remove users from a data source.
 * <p>
 * The implementation interacts with the database to perform CRUD operations on the `users` table.
 * </p>
 * 
 * @author Robin Guan (041117292)
 * @version 1.0
 * @since 17.0.8
 */
public class UserDAOImpl implements UserDAO{

    /**
     * Adds a new user to the data source.
     * 
     * @param userDTO The {@link UserDTO} object to be added.
     * @param conn SQL connection to the database.
     * @return {@code true} if the user was successfully added, {@code false} otherwise.
     * @throws SQLException if a database access error occurs or the SQL statement is invalid.
     */
    @Override
    public boolean addUser(UserDTO userDTO, Connection conn)  throws SQLException{
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO users (firstname, lastname, phone, "
                            + "email, password, type, organization) "
                            + "VALUES(?, ?, ?, ?, ?, ?, ?)")) {
           
            pstmt.setString(1, userDTO.getFirstName());
            pstmt.setString(2, userDTO.getLastName());
            pstmt.setString(3, userDTO.getPhone());
            pstmt.setString(4, userDTO.getEmail());
            pstmt.setString(5, userDTO.getPassword());
            pstmt.setInt(6, userDTO.getType());
            pstmt.setString(7, userDTO.getOrganization());
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }
        }
        
        return isSuccess;
    }

    /**
     * Retrieves a user by its ID.
     * 
     * @param userId The ID of the user to retrieve.
     * @param conn SQL connection to the database.
     * @return A {@link UserDTO} object representing the user if found, {@code null} otherwise.
     * @throws SQLException if a database access error occurs or the SQL statement is invalid.
     */
    @Override
    public UserDTO getUserById(int userId, Connection conn)  throws SQLException{
        UserDTO userDTO  = null;
        
        try(PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM users WHERE id = ?"
                    )) {
                                  
            pstmt.setInt(1, userId);           
            
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    userDTO = new UserDTO();
                    userDTO.setId(rs.getInt("id"));
                    userDTO.setFirstName(rs.getString("firstname"));
                    userDTO.setLastName(rs.getString("lastname"));
                    userDTO.setEmail(rs.getString("email"));
                    userDTO.setPhone(rs.getString("phone"));
                    userDTO.setPassword(rs.getString("password"));
                    userDTO.setType(rs.getInt("type"));
                    userDTO.setOrganization(rs.getString("organization"));
                }
            }            
        } 

        return userDTO;
    }
    
    /**
     * Retrieves a user by its email.
     * 
     * @param email The email of the user to retrieve.
     * @param conn SQL connection to the database.
     * @return A {@link UserDTO} object representing the user if found, {@code null} otherwise.
     * @throws SQLException if a database access error occurs or the SQL statement is invalid.
     */
    @Override
    public UserDTO getUserByEmail(String email, Connection conn)  throws SQLException{  
        UserDTO userDTO  = null;
        
        try(PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM users WHERE email = ?"
                    )) {
                        
            pstmt.setString(1, email);  
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    userDTO = new UserDTO();
                    userDTO.setId(rs.getInt("id"));
                    userDTO.setFirstName(rs.getString("firstname"));
                    userDTO.setLastName(rs.getString("lastname"));
                    userDTO.setEmail(rs.getString("email"));
                    userDTO.setPhone(rs.getString("phone"));
                    userDTO.setPassword(rs.getString("password"));
                    userDTO.setType(rs.getInt("type"));
                    userDTO.setOrganization(rs.getString("organization"));
                }
            }
        }
        
        return userDTO;
    }

    /**
     * Retrieves a user by its email and password.
     * 
     * @param email The email of the user to retrieve.
     * @param password The password of the user to retrieve.
     * @param conn SQL connection to the database.
     * @return A {@link UserDTO} object representing the user if found, {@code null} otherwise.
     * @throws SQLException if a database access error occurs or the SQL statement is invalid.
     */
    @Override
    public UserDTO getUserByEmailAndPassword(String email, String password, Connection conn) throws SQLException{
        UserDTO userDTO  = null;
        
        try(PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM users WHERE email = ? AND password = ?"
                    )) {
            
            pstmt.setString(1, email);    
            pstmt.setString(2, password);  
            
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    userDTO = new UserDTO();
                    userDTO.setId(rs.getInt("id"));
                    userDTO.setFirstName(rs.getString("firstname"));
                    userDTO.setLastName(rs.getString("lastname"));
                    userDTO.setEmail(rs.getString("email"));
                    userDTO.setPhone(rs.getString("phone"));
                    userDTO.setPassword(rs.getString("password"));
                    userDTO.setType(rs.getInt("type"));
                    userDTO.setOrganization(rs.getString("organization"));
                }
            }
        }            
            
        return userDTO;
    }

    /**
     * Updates an existing user in the data source.
     * 
     * @param userDTO The {@link UserDTO} object containing the new user data.
     * @param conn SQL connection to the database.
     * @return {@code true} if the user was successfully updated, {@code false} otherwise.
     * @throws SQLException if a database access error occurs or the SQL statement is invalid.
     */
    @Override
    public boolean updateUser(UserDTO userDTO, Connection conn) throws SQLException{        
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement(
                    "UPDATE users SET id = ?, firstname = ?, lastname = ?, "
                            + "phone = ?, email = ?, password = ?, type = ?, "
                            + "organization = ? WHERE id = ?")) {
            
            pstmt.setInt(1, userDTO.getId());
            pstmt.setString(2, userDTO.getFirstName());
            pstmt.setString(3, userDTO.getLastName());
            pstmt.setString(4, userDTO.getPhone());
            pstmt.setString(5, userDTO.getEmail());
            pstmt.setString(6, userDTO.getPassword());
            pstmt.setInt(7, userDTO.getType());
            pstmt.setString(8, userDTO.getOrganization());
            pstmt.setInt(1, userDTO.getId());
            
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }
        } 
        
        return isSuccess;
    }

    /**
     * Removes a user from the data source.
     * 
     * @param userDTO The {@link UserDTO} object of the user to be removed.
     * @param conn SQL connection to the database.
     * @return {@code true} if the user was successfully removed, {@code false} otherwise.
     * @throws SQLException if a database access error occurs or the SQL statement is invalid.
     */
    @Override
    public boolean removeUser(UserDTO userDTO, Connection conn) throws SQLException{      
        boolean isSuccess = false;

        try(PreparedStatement pstmt = conn.prepareStatement(
                    "DELETE FROM users WHERE id = ?")) {
            
            pstmt.setInt(1, userDTO.getId());
                        
            if(pstmt.executeUpdate() == 1){
                isSuccess = true;
            }
        }
        
        return isSuccess;
    }
    
}
