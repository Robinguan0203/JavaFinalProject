/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dto;

import com.fwrp.models.User;

/**
 *
 * @author robin
 */
public class UserDTO {
    private int id;    
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String password;
    private int type;
    private String organization;

    public UserDTO(){
        
    }
    
    public UserDTO(int id, String firstName, String lastName, String email, 
            String phone, String password, int type, String organization) {
        this.id = id;
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.type = type;
        this.organization = organization;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
    
    public User transferToUser(User user){
        user.setId(this.getId());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setEmail(this.getEmail());
        user.setPhone(this.getPhone());
        user.setPassword(this.getPassword());
        user.setType(this.getType());
        user.setOrganization(this.getOrganization());
        
        return user;
    }
}
