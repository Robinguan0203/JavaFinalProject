/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.exceptions;

/**
 * Custom exception to indicate that the requested data does not exist in the database.
 * This exception is thrown when an operation attempts to access or modify data that is
 * not present in the database.
 * 
 * <p>
 * Example use case: Throwing this exception when a query for a specific record by its ID
 * does not return any results.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 1.0
 */
public class DataNotExistsException extends Exception {
    
    /**
     * Constructs a new DataNotExistsException with the specified detail message.
     * 
     * @param message The detail message, which provides more information about the exception.
     */
    public DataNotExistsException(String message) {
        super(message);
    }
}
