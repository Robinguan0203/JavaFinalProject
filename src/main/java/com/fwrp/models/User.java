package com.fwrp.models;

import com.fwrp.dataaccess.dto.UserDTO;


public abstract class User {
    
    private int id;    
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private int type;
    private String organization;
    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    
    public UserDTO transferToUserDTO(){
        UserDTO userDTO = new UserDTO(this.getId(), 
                this.getFirstName(), 
                this.getLastName(), 
                this.getEmail(), 
                this.getPhone(), 
                this.getPassword(), 
                this.getType(), 
                this.getOrganization()
        );
        
        return userDTO;
    }
    
    abstract void login();
    abstract void logout();
    
}
