/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.services;

import com.fwrp.dataaccess.dto.SubscriptionDTO;
import com.fwrp.dbService.SubscriptionDbService;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Yaozhou XIe
 */
public class ConsumerService {
    public void addSubscription(int userId,int method) throws Exception{
        SubscriptionDbService dbService = new SubscriptionDbService();
        dbService.addSubscription(userId,method);
    }

    public List<SubscriptionDTO> getAllMethodsByUserId(int userId) throws ClassNotFoundException, SQLException {
        SubscriptionDbService dbService = new SubscriptionDbService();
        return dbService.getAllMethodsByUserId(userId);
    }

    public void deleteSubscription(int id) throws Exception{
        SubscriptionDbService dbService = new SubscriptionDbService();
        dbService.deleteSubscription(id);
    }
}
