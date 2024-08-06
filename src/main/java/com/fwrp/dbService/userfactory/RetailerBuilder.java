/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dbService.userfactory;

import com.fwrp.models.Retailer;

/**
 * A builder class for constructing {@link Retailer} objects.
 * <p>
 * This class uses the Builder design pattern to create instances of {@link Retailer} with
 * various attributes. The builder pattern provides a flexible and readable way to build
 * complex objects.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 1.0
 */
public class RetailerBuilder {
    /**
     * The ID of the retailer.
     */
    private int id;    
    
    /**
     * The first name of the retailer.
     */
    private String firstName;
    
     /**
     * The last name of the retailer.
     */
    private String lastname;
    
     /**
     * The email of the retailer.
     */
    private String email;
    
     /**
     * The phone number of the retailer.
     */
    private String phone;
    
    /**
     * The password of the retailer.
     */
    private String password;
    
    /**
     * The type of the retailer.
     */
    private int type;
    
    /**
     * The organization of the retailer.
     */
    private String organization;

     /**
     * Gets the ID of the retailer.
     * 
     * @return the ID of the retailer.
     */
    public int getId() {
        return id;
    }

     /**
     * Gets the first name of the retailer.
     * 
     * @return the first name of the retailer.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the retailer.
     * 
     * @return the last name of the retailer.
     */
    public String getLastName() {
        return lastname;
    }

    /**
     * Gets the email of the retailer.
     * 
     * @return the email of the retailer.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the phone number of the retailer.
     * 
     * @return the phone number of the retailer.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the password of the retailer.
     * 
     * @return the password of the retailer.
     */
    public String getPassword() {
        return password;
    }

     /**
     * Gets the type of the retailer.
     * 
     * @return the type of the retailer.
     */
    public int getType() {
        return type;
    }

     /**
     * Gets the organization of the retailer.
     * 
     * @return the organization of the retailer.
     */
    public String getOrganization() {
        return organization;
    }
    
    /**
     * Private constructor to enforce the use of the {@link #create()} method for object creation.
     */
    private RetailerBuilder(){}
    
    /**
     * Creates and returns a new instance of {@link RetailerBuilder}.
     * 
     * @return a new instance of {@link RetailerBuilder}.
     */
    public static RetailerBuilder create(){
        return new RetailerBuilder();
    }
    
    /**
     * Sets the ID of the retailer being built.
     * 
     * @param id the ID to set.
     * @return the current instance of {@link RetailerBuilder} for method chaining.
     */
    public RetailerBuilder id(int id){
        this.id = id;
        return this;
    }
    
    /**
     * Sets the first name of the retailer being built.
     * 
     * @param firstname the first name to set.
     * @return the current instance of {@link RetailerBuilder} for method chaining.
     */
    public RetailerBuilder firstname(String firstname){
        this.firstName = firstname;
        return this;
    }
    
    /**
     * Sets the last name of the retailer being built.
     * 
     * @param lastname the last name to set.
     * @return the current instance of {@link RetailerBuilder} for method chaining.
     */
    public RetailerBuilder lastname(String lastname){
        this.lastname = lastname;
        return this;
    }
    
    /**
     * Sets the email of the retailer being built.
     * 
     * @param email the email to set.
     * @return the current instance of {@link RetailerBuilder} for method chaining.
     */
    public RetailerBuilder email(String email){
        this.email = email;
        return this;
    }
    
     /**
     * Sets the phone number of the retailer being built.
     * 
     * @param phone the phone number to set.
     * @return the current instance of {@link RetailerBuilder} for method chaining.
     */
    public RetailerBuilder phone(String phone){
        this.phone = phone;
        return this;
    }
    
    /**
     * Sets the password of the retailer being built.
     * 
     * @param password the password to set.
     * @return the current instance of {@link RetailerBuilder} for method chaining.
     */
    public RetailerBuilder password(String password){
        this.password = password;
        return this;
    }
    
     /**
     * Sets the type of the retailer being built.
     * 
     * @param type the type to set.
     * @return the current instance of {@link RetailerBuilder} for method chaining.
     */    
    public RetailerBuilder type(int type){
        this.type = type;
        return this;
    }
    
    /**
     * Sets the organization of the retailer being built.
     * 
     * @param organization the organization to set.
     * @return the current instance of {@link RetailerBuilder} for method chaining.
     */
    public RetailerBuilder organization(String organization){
        this.organization = organization;
        return this;
    }
    
    /**
     * Builds and returns a new instance of {@link Retailer} with the specified attributes.
     * 
     * @return a new {@link Retailer} instance.
     */
    public Retailer build(){
        return new Retailer();
    }
    
}
