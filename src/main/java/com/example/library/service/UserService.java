package com.example.library.service;

import com.example.library.entity.User;
import com.example.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    //this method saves user to db (post method for db)
    public User saveUser(User user){
        return userRepo.save(user);
    }

    //this method saves all users to db (post method for db)
    public List<User> saveAllUsers(List<User> users){
        return userRepo.saveAll(users);
    }

    //fetch all users from db (get method for db)
    public List<User> allUsersBooks(){
        return userRepo.findAll();
    }

    public List<User> allUsers(){
        return userRepo.findAll();
    }

    //fetch one user from db (get method for db)
    public User findUserById(int id){
        return userRepo.findById(id).orElse(null);
    }

    //fetch one user by name from db
    public User findUserByEmail(String email){ return userRepo.findUserByEmail(email); }

    //delete one user from db
    public String delete(int id){
        userRepo.deleteById(id);
        return "User is deleted!";
    }

    //update user
    public User updateUser(User user){
        User existingUser = userRepo.findById(user.getId()).orElse(null);

        existingUser.setName(user.getName());
        existingUser.setSurname(user.getSurname());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());


        return userRepo.save(existingUser);

    }

}
