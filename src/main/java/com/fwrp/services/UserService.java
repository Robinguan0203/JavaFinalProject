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
    
    public int[] getNotificationCount(User user) throws SQLException, ClassNotFoundException {
        int[] count = new int[2];
        UserDbService userDbService = new UserDbService();
        
        count = userDbService.getNotificationCountByUser(user);
        
        return count;
    }

}
