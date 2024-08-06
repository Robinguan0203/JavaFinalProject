/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.services.observer;

import com.fwrp.services.NotificationService;
import com.fwrp.services.observer.NotificationObserver;
import com.fwrp.services.observer.ConsumerNotificationObserver;
import com.fwrp.services.observer.CharityNotificationObserver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * NotificationSubject manages a list of observers and notifies them of updates.
 * 
 * This class allows for registering and removing observers, as well as notifying all registered observers
 * with a given message. It also includes a method to register all observers based on charity and consumer
 * notification methods.
 * 
 * @autor Robin Guan
 */
public class NotificationSubject {
    
    /**
     * A list of registered observers.
     */
    public List<NotificationObserver> observers = new ArrayList<>();
    
    /**
     * Registers all observers based on charity and consumer notification methods.
     * 
     * This method retrieves notification methods for charities and consumers and registers observers
     * for each user and method.
     * 
     * @param foodId The ID of the food item used to retrieve consumer notification methods.
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the NotificationService class cannot be found
     */
    public void registerAllObservers(int foodId) throws SQLException, ClassNotFoundException{
        NotificationService service = new NotificationService();
        Map<Integer, List<Integer>> charitySubscriberMap = service.getNotificationMethodOfCharity();
        
        for (Map.Entry<Integer, List<Integer>> entry : charitySubscriberMap.entrySet()) {
            int userId = entry.getKey();
            List<Integer> methods = entry.getValue();
            for (int method : methods) {
                registerObserver(new CharityNotificationObserver(userId, method));
            }
        }
        
        Map<Integer, List<Integer>> consumerSubscriberMap = service.getNotificationMethodOfConsumetByFoodId(foodId);
        for (Map.Entry<Integer, List<Integer>> entry : consumerSubscriberMap.entrySet()) {
            int userId = entry.getKey();
            List<Integer> methods = entry.getValue();
            
            for(int method : methods){
                registerObserver(new ConsumerNotificationObserver(userId, method));
            }            
        }
        
    }
    
    /**
     * Clears all registered observers.
     * 
     * This method removes all observers from the list.
     */
    public void clearObservers() {
        observers.clear();
    }
    
    /**
     * Registers a new observer.
     * 
     * This method adds a new observer to the list of observers.
     * 
     * @param observer The observer to be registered.
     */
    public void registerObserver(NotificationObserver observer){
        observers.add(observer);
    }
    
    /**
     * Removes an existing observer.
     * 
     * This method removes an observer from the list of observers.
     * 
     * @param observer The observer to be removed.
     */
    public void removeObserver(NotificationObserver observer){
        observers.remove(observer);
    }
    
    /**
     * Notifies all registered observers with a given message.
     * 
     * This method calls the update method on each registered observer with the provided message.
     * 
     * @param message The notification message to be sent to all observers.
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if a required class cannot be found
     */
    public void notifyObservers(String message) throws SQLException, ClassNotFoundException{
        for(NotificationObserver observer : observers){
            observer.update(message);
        }
        
        clearObservers();
    }
}
