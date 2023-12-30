package com.APIX.user.controller;
import com.APIX.user.model.User;
import com.APIX.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/users")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
       if(UserService.addUser(user)){
           return ResponseEntity.status(HttpStatus.CREATED).body("User has been created successfully");
       }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid User");
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(UserService.getUsers());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> removeUserById(@PathVariable("id") long id) {
        if(UserService.removeUserById(id)){
            return ResponseEntity.status(HttpStatus.OK).body("User has been deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid User ID");

    }

    @GetMapping("/check")
    public ResponseEntity<?>checkUser(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password
    ) {
        User user = UserService.checkUserExits(email, password);
        if(user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid User Credentials");
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        User user = UserService.getUserById(id);
        if(user != null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid User ID");
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") long id ,@RequestBody User user) {
        User foundUser = UserService.getUserById(id);
        if(foundUser == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid User ID");
        user.setId(id);
        if(UserService.updateUser(user)){
            return ResponseEntity.ok("User has been updated successfully");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User could not be updated");

    }
}
