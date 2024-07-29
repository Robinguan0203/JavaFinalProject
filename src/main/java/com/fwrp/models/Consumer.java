package com.fwrp.models;

/**
 * This Consumer Model
 * 
 * @author YAOZHOU XIE
 * @version 1.0
 * @since 2024.07.27
 */
public class Consumer extends User {
    
    private int id;    
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private int type;
    
    @Override
    public void login() {
        
    }

    @Override
    public void logout() {
        
    }
       
}
