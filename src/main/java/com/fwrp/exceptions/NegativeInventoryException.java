/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.exceptions;

/**
 * Custom exception to indicate that an inventory operation has resulted in a negative inventory level.
 * This exception is thrown when an attempt to update inventory levels results in one or more quantities
 * becoming negative, which is not allowed.
 * 
 * <p>
 * Example use case: Throwing this exception when a transaction reduces the inventory quantity below zero.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 1.0
 */
public class NegativeInventoryException extends Exception {
    
    /**
     * Constructs a new NegativeInventoryException with the specified detail message.
     * 
     * @param message The detail message, which provides more information about the exception.
     */
    public NegativeInventoryException(String message) {
        super(message);
    }
}
