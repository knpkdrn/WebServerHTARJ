package org.example;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.example.security.Cryptography;
import org.example.security.KeyGen;
import org.example.security.KeyStorage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws IOException {

        SpringApplication app = new SpringApplication(Main.class);
        ConfigurableApplicationContext context = app.run(args);

        // listening to user input
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("$ ");
                String input = scanner.nextLine();

                if ("close".equalsIgnoreCase(input)) {
                    System.out.println("Shutting down the server...");

                    SpringApplication.exit(context);
                    System.exit(0);
                } else if("gen -rsa".equalsIgnoreCase(input)) {
                    try {
                        KeyGen.generateRSA();
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                } else if("gen -aes".equalsIgnoreCase(input)) {
                    try {
                        KeyGen.generateAES();
                        KeyGen.generateAES(0);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                } else if("set keys".equalsIgnoreCase(input)) {
                    try {
                        KeyStorage.setKeys();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else if("get keys".equalsIgnoreCase(input)) {
                    try {
                        KeyStorage.getKeys();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else if("print keys".equalsIgnoreCase(input)) {
                    try {
                        Cryptography.printKeys();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else if("frg master".equalsIgnoreCase(input)) {
                    try {
                        KeyStorage.getMasterKeyFromFiles();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

    }
}