/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.services;

import com.fwrp.dbService.UserDbService;
import com.fwrp.exceptions.DateNotExistsException;
import com.fwrp.models.User;
import java.sql.SQLException;

/**
 *
 * @author Ke Yan
 */
public class UserService {
    public User getUserByCredentials(String email, String password) throws DateNotExistsException, SQLException, ClassNotFoundException{
        UserDbService userDbService = new UserDbService();
        User user = userDbService.getLoginUser(email, password);
        
        return user;
    }
}
