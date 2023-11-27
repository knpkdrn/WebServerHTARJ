package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Scanner;

import org.example.security.Cryptography;
import org.example.security.KeyGen;
import org.example.security.KeyStorage;
import org.example.services.EmailService;
import org.example.tables.models.User;
import org.example.tables.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IOException {

        SpringApplication app = new SpringApplication(Main.class);
        ConfigurableApplicationContext context = app.run(args);
        EmailService emailService = context.getBean(EmailService.class);
        UserService userService = context.getBean(UserService.class);

        try {
            if (Files.exists(Paths.get("this_is_definitely_not_the_first_part_of_the_master_key.dat")) &&
                    Files.exists(Paths.get("this_is_definitely_not_the_second_part_of_the_master_key.dat"))){
                KeyStorage.getMasterKeyFromFiles();
                System.out.println(LocalDateTime.now() + ": Master key is forged.");
            }
            else {
                System.out.println(LocalDateTime.now() + ": Master key does not exist yet.");
            }
            if (Files.exists(Paths.get("these_are_not_the_keys_you_are_looking_for.dat"))){
                KeyStorage.getKeys();
                System.out.println(LocalDateTime.now() + ": Keys are forged");
            }
            else {
                System.out.println(LocalDateTime.now() + ": Keys do not exist yet.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
                }  else if ("crt user".equalsIgnoreCase(input)) {
                    User user = new User();
                    System.out.print("email: ");
                    user.setEmail(scanner.nextLine());
                    System.out.print("username: ");
                    user.setUsername(scanner.nextLine());
                    System.out.print("isAdmin: ");
                    input = scanner.nextLine();
                    if("false".equalsIgnoreCase(input)) {
                        user.setIsAdmin(false);
                    } else if("true".equalsIgnoreCase(input)) {
                        user.setIsAdmin(true);
                    }
                    input = user.generatePassword();
                    System.out.print("password: " + input);
                    user.setPassword(input);
                    System.out.println();

                    userService.save(user);

                    emailService.sendEmail(user.getEmail(), user.getPassword());

                } else {
                    System.out.println("input: " + input);
                    try {
                        input = Cryptography.encryptDataInRest(input);
                        System.out.println("encrypted input: " + input);

                        input = Cryptography.decryptDataInRest(input);
                        System.out.println("decrypted input: " + input);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

    }
}