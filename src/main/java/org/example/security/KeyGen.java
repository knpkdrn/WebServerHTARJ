package org.example.security;

import org.example.tables.models.User;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.*;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class KeyGen {

    public static void generateRSA() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey prKey = pair.getPrivate();
        PublicKey puKey = pair.getPublic();


        // Encode for printing out

        String encodedPublicKey = Base64.getEncoder().encodeToString(puKey.getEncoded());
        String encodedPrivateKey = Base64.getEncoder().encodeToString(prKey.getEncoded());

        System.out.println("Public key: " + encodedPublicKey);
        System.out.println("Private key: " + encodedPrivateKey);
    }

    public static void generateAES() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);

        SecretKey secretKey = keyGen.generateKey();

        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    public static String generateApiKey(List<User> users) {
        Boolean unique = true;
        String key;

        while(true) {
            key = UUID.randomUUID().toString();;
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
