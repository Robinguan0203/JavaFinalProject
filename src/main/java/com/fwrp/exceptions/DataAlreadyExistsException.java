/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.exceptions;

/**
 * Custom exception to indicate that the data already exists in the system.
 * This exception is thrown when an operation tries to insert or process data 
 * that already exists in the database or system, and thus violates unique constraints.
 * 
 * <p>
 * Example use case: Throwing this exception when attempting to add a new user 
 * with an email address that is already registered in the system.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 1.0
 */
public class DataAlreadyExistsException extends Exception {
    
     /**
     * Constructs a new DataAlreadyExistsException with the specified detail message.
     * 
     * @param message The detail message, which provides more information about the exception.
     */
    public DataAlreadyExistsException(String message) {
        super(message);
    }
}
