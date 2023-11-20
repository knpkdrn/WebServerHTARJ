package org.example.security;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.*;
import java.util.Base64;

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

}
