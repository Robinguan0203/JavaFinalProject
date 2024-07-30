/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.exceptions;

/**
 * Custom exception to indicate that data insertion into the database has failed.
 * This exception is thrown when an operation attempts to insert data into the database,
 * but the insertion process fails due to various reasons such as database constraints,
 * connection issues, or other unexpected errors.
 * 
 * <p>
 * Example use case: Throwing this exception when an attempt to add a new record to the database
 * fails due to a violation of a unique constraint.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 1.0
 */
public class DataInsertionFailedException extends Exception {
    
    /** 
    * @author Robin Guan
    * @version 1.0
    * @since 1.0
    */
    public DataInsertionFailedException(String message) {
        super(message);
    }
}
