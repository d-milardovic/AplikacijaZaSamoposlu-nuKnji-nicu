package com.example.library.controller;

import com.example.library.UserNotFoundException;
import com.example.library.entity.Rent;
import com.example.library.entity.User;
import com.example.library.repositories.UserRepository;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    UserRepository userRepository;


    //GET method display all users
    @GetMapping("/users")
    public List<User> allUsers(){
        return service.allUsers();
    }

    //GET method display one book by ID
    @GetMapping("/userById/{id}")
    public User getUserById(@PathVariable int id){
        return service.findUserById(id);
    }

    //GET method display one book by title
    @GetMapping("/user/{email}")
    public User getUserByEmail(@PathVariable String email){
        return service.findUserByEmail(email);
    }

    //POST method for adding one user
    @PostMapping("/addUser")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        User existingUser = userRepository.findUserByEmail(user.getEmail());
        if(existingUser != null){
            return new ResponseEntity<>("User with this email already exists!", HttpStatus.CONFLICT);
        }
         User newUser = service.saveUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    //POST method for adding all users
    @PostMapping("/addAllUsers")
    public List<User> addAllUsers(@RequestBody List<User> users){
        return service.saveAllUsers(users);
    }

    //PUT method for update book
    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user){
        return service.updateUser(user);
    }

    //DELETE method for deleting book
    @DeleteMapping("/deleteUser/{id}")
    public String delete(@PathVariable int id){
        return service.delete(id);
    }

    @PostMapping("/login")
    ResponseEntity<Object> login(@RequestBody User user) {
        List<User> userList = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (userList.size() != 1) {
            throw new UserNotFoundException("Entity not found");
        } else {
            return new ResponseEntity<>(userList.get(0), HttpStatus.OK);
        }
    }


}
