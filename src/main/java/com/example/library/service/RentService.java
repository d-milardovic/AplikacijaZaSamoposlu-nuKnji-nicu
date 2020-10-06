package com.example.library.service;

import com.example.library.entity.Rent;
import com.example.library.entity.User;
import com.example.library.repositories.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentService {

    @Autowired
    private RentRepository borrowRepo;

    //this method saves item to db (post method for db)
    public Rent saveItem(Rent rent){
        return borrowRepo.save(rent);
    }

    //this method saves all items to db (post method for db)
    public List<Rent> saveAllItems(List<Rent> rents){
        return borrowRepo.saveAll(rents);
    }

    //fetch all items from db (get method for db)
    public List<Rent> getItemByUserId(Integer userId){
        return borrowRepo.findByUserId(userId);
    }

    public List<Rent> getItemByUser(Optional<User> user){
        return borrowRepo.findByUser(user);
    }

    public List<Rent> getAllItems(){
        return borrowRepo.findAll();
    }

    //fetch one item from db (get method for db)
    public Rent getItem(Integer id){
        return borrowRepo.findById(id).orElse(null);
    }

    //delete one book from db


}
