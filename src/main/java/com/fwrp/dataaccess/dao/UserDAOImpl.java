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
 * This class implements the UserDAO interface to provide a concrete implementation.
 * It provides methods to add, retrieve, and remove users from a data source.
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public class UserDAOImpl implements UserDAO{

    /**
     * Adds a new user to the data source.
     * 
     * @param userDTO The userDTO object to be added.
     * @param conn SQL connection
     * @return Boolean Returns true if the course was successfully added, false otherwise.
     * @throws java.sql.SQLException
     */
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
