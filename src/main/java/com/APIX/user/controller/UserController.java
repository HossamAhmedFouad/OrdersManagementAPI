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
       if(userService.addUser(user)){
           return ResponseEntity.status(HttpStatus.CREATED).build();
       }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        try {
            List<User>users=userService.getUsers();
            if(users!=null){
                return  ResponseEntity.ok(users);
            }
            return ResponseEntity.noContent().build();
        }catch (Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> removeUserById(@PathVariable("id") long id) {
        if(userService.removeUserById(id)){
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping(path = "{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User user=userService.getUserById(id);
        if(user!=null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();


    }

    @PutMapping(path = "{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id ,@RequestBody User user) {
        User updatedUser=userService.updateUser(id,user);
        if(updatedUser!=null){
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();

    }
}
