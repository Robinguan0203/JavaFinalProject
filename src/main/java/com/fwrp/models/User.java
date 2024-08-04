package com.fwrp.models;

import com.fwrp.dataaccess.dto.UserDTO;

/**
 * Represents an abstract User class.
 * This class serves as a base class for different types of users and includes common properties and methods.
 */
public abstract class User {
    
	/**
     * The user ID.
     */
    private int id;    
	
	/**
     * The first name of the user.
     */
    private String firstName;
	
	/**
     * The last name of the user.
     */
    private String lastName;
	
	/**
     * The email of the user.
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
     * The type of the user.
     */
    private int type;
	
	/**
     * The organization of the user.
     */
    private String organization;
    
    /**
     * Gets the user ID.
     * 
     * @return the user ID.
     */
    public int getId() {
        return id;
    }

	/**
     * Sets the user ID.
     * 
     * @param id the user ID.
     */
    public void setId(int id) {
        this.id = id;
    }

	/**
     * Gets the first name of the user.
     * 
     * @return the first name.
     */
    public String getFirstName() {
        return firstName;
    }
	
	/**
     * Sets the first name of the user.
     * 
     * @param firstName the first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
	
	/**
     * Gets the last name of the user.
     * 
     * @return the last name.
     */
    public String getLastName() {
        return lastName;
    }
	
	/**
     * Sets the last name of the user.
     * 
     * @param lastName the last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
	
	/**
     * Gets the email of the user.
     * 
     * @return the email.
     */
    public String getEmail() {
        return email;
    }

	/**
     * Sets the email of the user.
     * 
     * @param email the email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
	
	/**
     * Gets the phone number of the user.
     * 
     * @return the phone number.
     */
    public String getPhone() {
        return phone;
    }
	
	/**
     * Sets the phone number of the user.
     * 
     * @param phone the phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
	
	/**
     * Gets the password of the user.
     * 
     * @return the password.
     */
    public String getPassword() {
        return password;
    }
	
	/**
     * Sets the password of the user.
     * 
     * @param password the password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
	
	/**
     * Gets the type of the user.
     * 
     * @return the type.
     */
    public int getType() {
        return type;
    }
	
	/**
     * Sets the type of the user.
     * 
     * @param type the type.
     */
    public void setType(int type) {
        this.type = type;
    }

	/**
     * Gets the organization of the user.
     * 
     * @return the organization.
     */
    public String getOrganization() {
        return organization;
    }

	/**
     * Sets the organization of the user.
     * 
     * @param organization the organization.
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }
    
	/**
     * Transfers the current User object to a UserDTO object.
     * 
     * @return UserDTO The data transfer object representing the user.
     */
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
}
