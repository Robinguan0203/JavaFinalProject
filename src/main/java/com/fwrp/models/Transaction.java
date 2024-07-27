package com.fwrp.models;

import com.fwrp.dataaccess.dto.TransactionDTO;
import com.fwrp.exceptions.NegativeInventoryException;
import java.sql.SQLException;
import java.util.Date;

public abstract class Transaction {
    private int id;
    private Food food;
    private User user;
    private Date date;
    private int type;

    public int getId() {
        return id;
    }

    public Food getFood() {
        return food;
    }

    public User getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public int getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public abstract TransactionDTO createTransactionDTO();
    public abstract void storeTransaction()throws NegativeInventoryException, SQLException;
    public abstract void updateExpireInfo() throws SQLException;
    
}
