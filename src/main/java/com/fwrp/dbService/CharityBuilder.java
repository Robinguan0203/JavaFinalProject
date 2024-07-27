/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dbService;

import com.fwrp.models.Charity;

/**
 *
 * @author robin
 */
public class CharityBuilder {
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
    private CharityBuilder(){}
    
    public static CharityBuilder create(){
        return new CharityBuilder();
    }
    
    public CharityBuilder id(int id){
        this.id = id;
        return this;
    }
    
    public CharityBuilder firstname(String firstname){
        this.firstName = firstname;
        return this;
    }
    
    public CharityBuilder lastname(String lastname){
        this.lastname = lastname;
        return this;
    }
    
    public CharityBuilder email(String email){
        this.email = email;
        return this;
    }
    
    public CharityBuilder phone(String phone){
        this.phone = phone;
        return this;
    }
    
    public CharityBuilder password(String password){
        this.password = password;
        return this;
    }
    
    public CharityBuilder type(int type){
        this.type = type;
        return this;
    }
    
    public CharityBuilder organization(String organization){
        this.organization = organization;
        return this;
    }
    
    public Charity build(){
        return new Charity();
    }
    
}
