/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shiftreportjava;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author snowyowl
 */
public class User implements Serializable{
    private String Address;
    private String username;
    private HashMap<String,String> labs = new HashMap<>();

    
    public User(String email){
        setAddress(email);
        
    }
    public User(String email, HashMap<String,String> Labs){
        setLabs(Labs);
        setAddress(email);
    }
    public User(){}
    
    public String getAddress() {
        return Address;
    }
    
    public void setAddress(String Address) {
        this.Address = Address;
        setUsername();
    }
    public HashMap<String,String> getLabs() {
        return labs;
    }

    public void setLabs(HashMap<String,String> labs) {
        this.labs = labs;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername() {
        username = Address.split("@")[0];
    }
    
   
}
