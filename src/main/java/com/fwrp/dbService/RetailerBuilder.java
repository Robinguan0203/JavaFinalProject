/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dbService;

import com.fwrp.models.Retailer;

/**
 *
 * @author robin
 */
public class RetailerBuilder {
    private int id;    
    private String firstName;
    private String lastname;
    private String email;
    private String phone;
    private String password;
    private int type;
    private String organization;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public int getType() {
        return type;
    }

    public String getOrganization() {
        return organization;
    }
    
    /**
     * Constructor was marked private to enforce use of create() method
     */
    private RetailerBuilder(){}
    
    public static RetailerBuilder create(){
        return new RetailerBuilder();
    }
    
    public RetailerBuilder id(int id){
        this.id = id;
        return this;
    }
    
    public RetailerBuilder firstname(String firstname){
        this.firstName = firstname;
        return this;
    }
    
    public RetailerBuilder lastname(String lastname){
        this.lastname = lastname;
        return this;
    }
    
    public RetailerBuilder email(String email){
        this.email = email;
        return this;
    }
    
    public RetailerBuilder phone(String phone){
        this.phone = phone;
        return this;
    }
    
    public RetailerBuilder password(String password){
        this.password = password;
        return this;
    }
    
    public RetailerBuilder type(int type){
        this.type = type;
        return this;
    }
    
    public RetailerBuilder organization(String organization){
        this.organization = organization;
        return this;
    }
    
    public Retailer build(){
        return new Retailer();
    }
    
}
