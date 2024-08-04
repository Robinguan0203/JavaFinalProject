/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp;
import com.fwrp.models.Transaction;
import com.fwrp.services.ConsumerService;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ke Yan
 */
public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConsumerService consumerService = new ConsumerService();
        ArrayList<Transaction> transactions = consumerService.getTransactionsByUserId(3);
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getFood());
        }
        System.out.println("------------------");
    }
}
