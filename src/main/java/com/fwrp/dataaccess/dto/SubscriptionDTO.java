/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dto;


/**
 *
 * @author Siqian
 */
public class SubscriptionDTO {
    private int id;
    private int userId;
    private int method;


    public int getId() {
        return id;
    }
    public int getUserId() {
        return userId;
    }
    public int getMethod() {
        return method;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public SubscriptionDTO() {

    }
}
