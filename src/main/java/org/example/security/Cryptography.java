package org.example.security;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;

public class Cryptography {

    /**
     * Key for encrypt and decrypt data in database
     */
    private static final String keyForRest = "";


    /**
     * Public RSA key for the data that is received
     * */
    private static String privateKeyForTransit = "";

    /**
     * Public RSA key for the data that is sent
     * */
    private static String publicKeyForTransit = "";


    public static String encryptDataInRest(String data) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        byte[] bytes = keyGenerator.generateKey().getEncoded();
        privateKeyForTransit = "asd";
        return bytes.toString();
    }

    public static String decryptDataInRest(String data) {

    }
}
