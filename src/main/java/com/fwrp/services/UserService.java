/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.services;

import com.fwrp.dataaccess.dto.UserDTO;
import com.fwrp.dbService.UserDbService;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.exceptions.DataNotExistsException;
import com.fwrp.models.User;
import java.sql.SQLException;

/**
 *
 * @author Ke Yan
 */
public class UserService {
    public User getUserByCredentials(String email, String password) throws DataNotExistsException, SQLException, ClassNotFoundException{
        UserDbService userDbService = new UserDbService();
        User user = userDbService.getLoginUser(email, password);
        
        return user;
    }
    
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
}
