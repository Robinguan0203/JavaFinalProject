package com.fwrp.dataaccess.dao;

import com.fwrp.dataaccess.dto.TransactionDTO;
import com.fwrp.models.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This interface defines the standard operations to be performed on Transaction model object(s).
 * It provides methods to add, retrieve, and remove transactions from a data source.
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
     * @return boolean Returns true if the transaction was successfully added, false otherwise.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     */
    boolean addTransaction(TransactionDTO transactionDTO, Connection conn)  throws SQLException ;
    
    /**
     * Deletes a transaction from the data source.
     * 
     * @param transactionDTO The transaction object to be deleted.
     * @param conn SQL connection
     * @return boolean Returns true if the transaction was successfully deleted, false otherwise.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     */
    boolean deleteTransaction(TransactionDTO transactionDTO, Connection conn)  throws SQLException ;
    
    /**
     * Retrieves all transactions from the data source.
     * 
     * @param conn SQL connection
     * @return ArrayList<TransactionDTO> A list of all transactions.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     */
    ArrayList<TransactionDTO> getAllTransactions(Connection conn) throws SQLException;
    
	/**
     * Retrieves transactions by user ID from the data source.
     * 
     * @param userId The ID of the user.
     * @param conn The SQL connection used to access the database.
     * @return ArrayList<Transaction> A list of transactions for the specified user.
     * @throws SQLException if a database access error occurs or the SQL query fails.
     * @throws ClassNotFoundException if the Transaction class is not found.
     */
    ArrayList<Transaction> getTransactionsByUserId(int userId, Connection conn) throws SQLException, ClassNotFoundException;
}
