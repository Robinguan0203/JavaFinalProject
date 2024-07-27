/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dbService;

import com.fwrp.dataaccess.DataSource;
import com.fwrp.dataaccess.dao.UserDAO;
import com.fwrp.dataaccess.dao.UserDAOImpl;
import com.fwrp.dataaccess.dto.UserDTO;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.exceptions.DataNotExistsException;
import com.fwrp.models.User;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author robin
 */
public class UserDbService {
    private UserDAO userDAO = null;
    
    /**
     * Constructs a DatabaseOperation object and initializes DAO objects.
     */
    public UserDbService(){
        userDAO = new UserDAOImpl();        
    }
    
    public boolean AddUser(User user) throws DataAlreadyExistsException, DataInsertionFailedException, SQLException, ClassNotFoundException {
        Connection conn = null;
        boolean isUserAdded = false;
        UserDTO userToAddDTO = user.transferToUserDTO();
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
                    e.printStackTrace(); // Log the exception if closing connection fails
                }
            }
        }
               
        return isUserAdded;
    }
    
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
                    e.printStackTrace(); // Log the exception if closing connection fails
                }
            }
        }
        
        return user;
    }
}
