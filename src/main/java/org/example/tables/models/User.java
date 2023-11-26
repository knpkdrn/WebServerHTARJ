package org.example.tables.models;

import org.example.tables.services.UserService;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {
    private String password;
    private String username;
    private String email;
    private Boolean isAdmin;


    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }

    public String generatePassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase();
        String digits = "0123456789";
        String specialChar = "/.?&";
        String allowedChars = upper + lower + digits + specialChar;
        int length = 7;
        Random rnd = new Random();
        String result = "";
        for(int i = 0; i < length; ++i){
            result += allowedChars.toCharArray()[rnd.nextInt(allowedChars.length())];
        }

        return result;
    }
}
