/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fwrp.dbService;

import com.fwrp.models.User;
import java.sql.SQLException;

/**
 *
 * @author robin
 */
public interface NotificationCountObserver {
    int getNotificationCount(User user) throws SQLException, ClassNotFoundException;
}
