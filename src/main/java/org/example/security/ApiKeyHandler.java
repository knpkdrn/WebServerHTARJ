package org.example.security;

import org.example.tables.models.User;

import java.util.List;
import java.util.UUID;

public class ApiKeyHandler {
    public static String generateApiKey() {
        return UUID.randomUUID().toString();
    }
    public static String generateApiKey(List<User> users) {
        Boolean unique = true;
        String key;
        while(true) {
            key = generateApiKey();
            for (User u : users) {
                if(u.getApiKey().equals(key)) {
                    unique = false;
                    break;
                }
            }
            if(unique) {
               break;
            }

            unique = true;
        }

        return key;
    }
}
