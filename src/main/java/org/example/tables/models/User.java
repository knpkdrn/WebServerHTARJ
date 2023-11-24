package org.example.tables.models;

import org.example.tables.services.UserService;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {
    private String apiKey;
    private String password;
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
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
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
    public String getApiKey() {
        return apiKey;
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

    /** Checks if the user's is_admin field in the SQL table is true or false
     * @return True: if the user is an admin. False: if the user is not an admin
     * */
    public static Boolean CheckIfAdmin(String apiKey) {
        UserService userService = new UserService();
        User user = userService.getByKey(apiKey);

        return user != null;
    }

}
