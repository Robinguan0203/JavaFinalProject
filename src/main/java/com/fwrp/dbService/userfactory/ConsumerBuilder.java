/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dbService.userfactory;

import com.fwrp.models.Consumer;

/**
 * A builder class for constructing {@link Consumer} objects.
 * <p>
 * This class uses the Builder design pattern to create instances of {@link Consumer} with
 * various attributes. The builder pattern is used to allow for a flexible and readable
 * construction of complex objects.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 1.0
 */
public class ConsumerBuilder {
    
     /**
     * The ID of the consumer.
     */
    private int id;    
    
    /**
     * The first name of the consumer.
     */
    private String firstName;
    
    /**
     * The last name of the consumer.
     */
    private String lastname;
    
     /**
     * The email of the consumer.
     */
    private String email;
    
     /**
     * The phone number of the consumer.
     */
    private String phone;
    
     
    /**
     * The password of the consumer.
     */
    private String password;
    
    /**
     * The type of the consumer.
     */
    private int type;
    
    /**
     * The organization of the consumer, if applicable.
     */
    private String organization;
    
    /**
     * Gets the ID of the consumer.
     * 
     * @return the ID of the consumer.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the first name of the consumer.
     * 
     * @return the first name of the consumer.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the consumer.
     * 
     * @return the last name of the consumer.
     */
    public String getLastName() {
        return lastname;
    }

     /**
     * Gets the email of the consumer.
     * 
     * @return the email of the consumer.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the phone number of the consumer.
     * 
     * @return the phone number of the consumer.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the password of the consumer.
     * 
     * @return the password of the consumer.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the type of the consumer.
     * 
     * @return the type of the consumer.
     */
    public int getType() {
        return type;
    }

    /**
     * Gets the organization of the consumer.
     * 
     * @return the organization of the consumer.
     */
    public String getOrganization() {
        return organization;
    }
    
    /**
     * Private constructor to enforce the use of the {@link #create()} method for object creation.
     */
    private ConsumerBuilder(){}
    
    /**
     * Creates and returns a new instance of {@link ConsumerBuilder}.
     * 
     * @return a new instance of {@link ConsumerBuilder}.
     */
    public static ConsumerBuilder create(){
        return new ConsumerBuilder();
    }
    
    /**
     * Sets the ID of the consumer being built.
     * 
     * @param id the ID to set.
     * @return the current instance of {@link ConsumerBuilder} for method chaining.
     */
    public ConsumerBuilder id(int id){
        this.id = id;
        return this;
    }
    
    /**
     * Sets the first name of the consumer being built.
     * 
     * @param firstname the first name to set.
     * @return the current instance of {@link ConsumerBuilder} for method chaining.
     */
    public ConsumerBuilder firstname(String firstname){
        this.firstName = firstname;
        return this;
    }
    
    /**
     * Sets the last name of the consumer being built.
     * 
     * @param lastname the last name to set.
     * @return the current instance of {@link ConsumerBuilder} for method chaining.
     */
    public ConsumerBuilder lastname(String lastname){
        this.lastname = lastname;
        return this;
    }
    
    /**
     * Sets the email of the consumer being built.
     * 
     * @param email the email to set.
     * @return the current instance of {@link ConsumerBuilder} for method chaining.
     */
    public ConsumerBuilder email(String email){
        this.email = email;
        return this;
    }
    
    /**
     * Sets the phone number of the consumer being built.
     * 
     * @param phone the phone number to set.
     * @return the current instance of {@link ConsumerBuilder} for method chaining.
     */
    public ConsumerBuilder phone(String phone){
        this.phone = phone;
        return this;
    }
    
    /**
     * Sets the password of the consumer being built.
     * 
     * @param password the password to set.
     * @return the current instance of {@link ConsumerBuilder} for method chaining.
     */
    public ConsumerBuilder password(String password){
        this.password = password;
        return this;
    }
    
    /**
     * Sets the type of the consumer being built.
     * 
     * @param type the type to set.
     * @return the current instance of {@link ConsumerBuilder} for method chaining.
     */
    public ConsumerBuilder type(int type){
        this.type = type;
        return this;
    }
    
    /**
     * Sets the organization of the consumer being built.
     * 
     * @param organization the organization to set.
     * @return the current instance of {@link ConsumerBuilder} for method chaining.
     */
    public ConsumerBuilder organization(String organization){
        this.organization = organization;
        return this;
    }
    
    /**
     * Builds and returns a new instance of {@link Consumer} with the specified attributes.
     * 
     * @return a new {@link Consumer} instance.
     */
    public Consumer build(){
        return new Consumer();
    }
    
}
