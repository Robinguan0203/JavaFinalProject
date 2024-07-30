/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dbService;

import com.fwrp.models.Charity;

/**
 * A builder class for constructing {@link Charity} objects.
 * <p>
 * This class uses the Builder design pattern to create instances of {@link Charity} with
 * various attributes. The builder pattern is used to allow for a flexible and readable
 * construction of complex objects.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 1.0
 */
public class CharityBuilder {
    /**
     * The ID of the charity.
     */
    private int id;    
    
     /**
     * The first name of the charity.
     */
    private String firstName;
    
    /**
     * The last name of the charity.
     */
    private String lastname;
    
    /**
     * The email of the charity.
     */
    private String email;
    
    /**
     * The phone number of the charity.
     */
    private String phone;
    
    /**
     * The password of the charity.
     */
    private String password;
    
    /**
     * The type of the charity.
     */
    private int type;
    
     /**
     * The organization of the charity.
     */
    private String organization;

    /**
     * Gets the ID of the charity.
     * 
     * @return the ID of the charity.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the first name of the charity.
     * 
     * @return the first name of the charity.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the charity.
     * 
     * @return the last name of the charity.
     */
    public String getLastName() {
        return lastname;
    }

    /**
     * Gets the email of the charity.
     * 
     * @return the email of the charity.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the phone number of the charity.
     * 
     * @return the phone number of the charity.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the password of the charity.
     * 
     * @return the password of the charity.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the type of the charity.
     * 
     * @return the type of the charity.
     */
    public int getType() {
        return type;
    }

    /**
     * Gets the organization of the charity.
     * 
     * @return the organization of the charity.
     */
    public String getOrganization() {
        return organization;
    }
    
    /**
     * Private constructor to enforce the use of the {@link #create()} method for object creation.
     */
    private CharityBuilder(){}
    
    /**
     * Creates and returns a new instance of {@link CharityBuilder}.
     * 
     * @return a new instance of {@link CharityBuilder}.
     */
    public static CharityBuilder create(){
        return new CharityBuilder();
    }
    
    /**
     * Sets the ID of the charity being built.
     * 
     * @param id the ID to set.
     * @return the current instance of {@link CharityBuilder} for method chaining.
     */
    public CharityBuilder id(int id){
        this.id = id;
        return this;
    }
    
    /**
     * Sets the first name of the charity being built.
     * 
     * @param firstname the first name to set.
     * @return the current instance of {@link CharityBuilder} for method chaining.
     */
    public CharityBuilder firstname(String firstname){
        this.firstName = firstname;
        return this;
    }
    
    /**
     * Sets the last name of the charity being built.
     * 
     * @param lastname the last name to set.
     * @return the current instance of {@link CharityBuilder} for method chaining.
     */
    public CharityBuilder lastname(String lastname){
        this.lastname = lastname;
        return this;
    }
    
    /**
     * Sets the email of the charity being built.
     * 
     * @param email the email to set.
     * @return the current instance of {@link CharityBuilder} for method chaining.
     */
    public CharityBuilder email(String email){
        this.email = email;
        return this;
    }
    
    /**
     * Sets the phone number of the charity being built.
     * 
     * @param phone the phone number to set.
     * @return the current instance of {@link CharityBuilder} for method chaining.
     */
    public CharityBuilder phone(String phone){
        this.phone = phone;
        return this;
    }
    
    /**
     * Sets the password of the charity being built.
     * 
     * @param password the password to set.
     * @return the current instance of {@link CharityBuilder} for method chaining.
     */
    public CharityBuilder password(String password){
        this.password = password;
        return this;
    }
    
    /**
     * Sets the type of the charity being built.
     * 
     * @param type the type to set.
     * @return the current instance of {@link CharityBuilder} for method chaining.
     */
    public CharityBuilder type(int type){
        this.type = type;
        return this;
    }
    
    /**
     * Sets the organization of the charity being built.
     * 
     * @param organization the organization to set.
     * @return the current instance of {@link CharityBuilder} for method chaining.
     */
    public CharityBuilder organization(String organization){
        this.organization = organization;
        return this;
    }
    
    /**
     * Builds and returns a new instance of {@link Charity} with the specified attributes.
     * 
     * @return a new {@link Charity} instance.
     */
    public Charity build(){
        return new Charity();
    }
    
}
