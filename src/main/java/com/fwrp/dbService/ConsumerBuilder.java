/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dbService;

import com.fwrp.models.Consumer;

/**
 *
 * @author robin
 */
public class ConsumerBuilder {
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
    private ConsumerBuilder(){}
    
    public static ConsumerBuilder create(){
        return new ConsumerBuilder();
    }
    
    public ConsumerBuilder id(int id){
        this.id = id;
        return this;
    }
    
    public ConsumerBuilder firstname(String firstname){
        this.firstName = firstname;
        return this;
    }
    
    public ConsumerBuilder lastname(String lastname){
        this.lastname = lastname;
        return this;
    }
    
    public ConsumerBuilder email(String email){
        this.email = email;
        return this;
    }
    
    public ConsumerBuilder phone(String phone){
        this.phone = phone;
        return this;
    }
    
    public ConsumerBuilder password(String password){
        this.password = password;
        return this;
    }
    
    public ConsumerBuilder type(int type){
        this.type = type;
        return this;
    }
    
    public ConsumerBuilder organization(String organization){
        this.organization = organization;
        return this;
    }
    
    public Consumer build(){
        return new Consumer();
    }
    
}
