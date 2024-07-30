/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dto;

import com.fwrp.models.User;

/**
 * Data Transfer Object (DTO) for managing user data.
 * This class encapsulates the details of a user, including their ID, first name,
 * last name, email, phone number, password, user type, and organization.
 * <p>
 * This class provides methods to get and set properties for managing user data and
 * to transfer data to a {@link User} object.
 * </p>
 * 
 * @author robin
 * @version 1.0
 * @since 1.0
 */
public class UserDTO {
    
    /**
     * The unique identifier for the user.
     */
    private int id;    
    
    /**
     * The first name of the user.
     */
    private String firstname;
    
     /**
     * The last name of the user.
     */
    private String lastname;
    
    /**
     * The email address of the user.
     */
    private String email;
    
    /**
     * The phone number of the user.
     */
    private String phone;
    
    /**
     * The password of the user.
     */
    private String password;
    
    /**
     * The type of the user (e.g., retailer, consumer, charity).
     */
    private int type;
    
    /**
     * The organization associated with the user.
     */
    private String organization;

    
     /**
     * Default constructor.
     */
    public UserDTO(){
        
    }
    
    /**
     * Parameterized constructor to initialize all fields.
     * 
     * @param id The unique identifier for the user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param email The email address of the user.
     * @param phone The phone number of the user.
     * @param password The password of the user.
     * @param type The type of the user.
     * @param organization The organization associated with the user.
     */
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
    
    /**
     * Gets the unique identifier for the user.
     * 
     * @return The ID of the user.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the user.
     * 
     * @param id The ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name of the user.
     * 
     * @return The first name of the user.
     */
    public String getFirstName() {
        return firstname;
    }

    /**
     * Sets the first name of the user.
     * 
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    /**
     * Gets the last name of the user.
     * 
     * @return The last name of the user.
     */
    public String getLastName() {
        return lastname;
    }

    /**
     * Sets the last name of the user.
     * 
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    /**
     * Gets the email address of the user.
     * 
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     * 
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the user.
     * 
     * @return The phone number of the user.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the user.
     * 
     * @param phone The phone number to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the password of the user.
     * 
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * 
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the type of the user.
     * 
     * @return The type of the user.
     */
    public int getType() {
        return type;
    }

    /**
     * Sets the type of the user.
     * 
     * @param type The type to set.
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Gets the organization associated with the user.
     * 
     * @return The organization of the user.
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Sets the organization associated with the user.
     * 
     * @param organization The organization to set.
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }
    
    /**
     * Transfers the data from this DTO to a {@link User} object.
     * 
     * @param user The {@link User} object to transfer data to.
     * @return The {@link User} object with updated data.
     */
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
