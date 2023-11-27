package org.example.api.controllers;

import okhttp3.Response;
import org.example.tables.models.User;
import org.example.tables.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping("/logIn/{email}&{password}")
    public ResponseEntity<User> getLogIn(@PathVariable String email, @PathVariable String password) {
        User user = userService.validateLogIn(email, password);
        if(user != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/updatePassword/{email}&{newPassword}")
    public ResponseEntity<User> changePassword(@PathVariable String email, @PathVariable String newPassword) {

        if(userService.updatePassword(email, newPassword)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
