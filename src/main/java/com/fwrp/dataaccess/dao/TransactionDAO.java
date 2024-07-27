package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.TransactionDTO;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This interface defines the standard operations to be performed on Transaction model object(s).
 * It provides methods to add, retrieve, and remove user from a data source.
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public interface TransactionDAO{
    /**
     * Adds a new transaction to the data source.
     * 
     * @param transactionDTO The transaction object to be added.
     * @param conn SQL connection
     * @return Boolean Returns true if the user was successfully added, false otherwise.
     * @throws java.sql.SQLException
     */
    boolean addTransaction(TransactionDTO transactionDTO, Connection conn)  throws SQLException ;
    
    /**
     * Delete a transaction to the data source.
     * 
     * @param transactionDTO The transaction object to be deleted.
     * @param conn SQL connection
     * @return Boolean Returns true if the user was successfully added, false otherwise.
     * @throws java.sql.SQLException
     */
    boolean deleteTransaction(TransactionDTO transactionDTO, Connection conn)  throws SQLException ;
}
