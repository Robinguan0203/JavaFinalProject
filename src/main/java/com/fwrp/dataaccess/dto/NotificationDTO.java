/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dto;

import com.fwrp.dbService.FoodDbService;
import com.fwrp.dbService.UserDbService;
import com.fwrp.models.Consumer;
import com.fwrp.models.ExpireInfo;
import com.fwrp.models.Food;
import com.fwrp.models.Notification;
import com.fwrp.models.User;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author robin
 */
public class NotificationDTO {    
    private int id;
    private int method;
    private Date date;
    private String notification;
    private int userId;

    public int getId() {
        return id;
    }

    public int getMethod() {
        return method;
    }

    public Date getDate() {
        return date;
    }

    public String getNotification() {
        return notification;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Notification transferToNotification() throws SQLException, ClassNotFoundException{
        Notification notification = new Notification();
        UserDbService userDbService = new UserDbService();
        User user = null;
        try{
            user = userDbService.getUserById(this.getUserId());
        } catch (SQLException e){
            throw e;
        }
        
        notification.setId(this.getId());
        notification.setUser(user);
        notification.setMethod(this.getMethod());
        notification.setDate(this.getDate());
        notification.setNotification(this.getNotification());
                
        return notification;
    }
    
}
