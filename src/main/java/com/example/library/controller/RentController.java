package com.example.library.controller;


import com.example.library.RentBody;
import com.example.library.UserNotFoundException;
import com.example.library.entity.Book;
import com.example.library.entity.Rent;
import com.example.library.entity.User;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.RentRepository;
import com.example.library.repositories.UserRepository;


import com.example.library.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@RestController
public class RentController {

    @Autowired
    RentRepository rentRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RentService rentService;

    @GetMapping("/items")
    public List<Rent> allItems(){
        return rentService.getAllItems();
    }

    @PostMapping("/addRent")
    @Transactional
    public ResponseEntity<Object> addNewRent(@RequestBody RentBody rentBody){
        Optional<User> userOptional = userRepository.findById(rentBody.getUserId());
        Optional<Book> bookOptional = bookRepository.findById(rentBody.getBookId());

        if(!userOptional.isPresent() || !bookOptional.isPresent()){
            throw new UserNotFoundException("Entity not found");
        }

        List<Rent> existingRents = rentService.getAllItems();

        List<Rent> borrowed = rentRepository.findByUser(userOptional);
        int rentLimit = 3;
        int rentCount = 0;
        int rentBooksLimit = 5;
        int counter = 0;

        for (Rent rent : borrowed){
            if(rent.getRentEnd() == null) {
                counter++;
            }
        }
        if(counter >= rentBooksLimit){
            return new ResponseEntity<>(new Rent(), HttpStatus.OK);
        }
        for (Rent rent : existingRents) {
            if (rent.getBook().getId() == rentBody.getBookId() && rent.getRentEnd() == null) {
                rentCount++;
                if (rentCount >= rentLimit) {
                    return new ResponseEntity<>(new Rent(), HttpStatus.OK);
                }
            }
        }

        Rent rent = new Rent();
        rent.setUser(userOptional.get());

        Book bookDb = bookOptional.get();
        rent.setBook(bookDb);
        rent.setRentStart(LocalDateTime.now());

        return new ResponseEntity<>(rentRepository.save(rent), HttpStatus.OK);
    }

    @GetMapping("/rentUser/{userId}")
    public ResponseEntity<Object> getRentedBooksForUser(@PathVariable Integer userId){
        Optional<User> userOptional = userRepository.findById(userId);
        List<Rent> borrowed = rentRepository.findByUser(userOptional);

        return new ResponseEntity<>(borrowed, HttpStatus.OK);
    }

    @PutMapping("/rent/{rentId}")
    @Transactional
    public ResponseEntity<Object> returnBook(@PathVariable Integer rentId){
        Optional<Rent> borrowResult = rentRepository.findById(rentId);
        if(!borrowResult.isPresent()){
            throw new UserNotFoundException("Book is not borrowed");
        }

        Rent rent = borrowResult.get();
        rent.setRentEnd(LocalDateTime.now());

        return new ResponseEntity<>(rentRepository.save(rent), HttpStatus.OK);
    }

    @DeleteMapping("/deleteRent/{rentId}")
    public ResponseEntity<Object> deleteItem(@PathVariable Integer rentId){

        if(!rentRepository.existsById(rentId)){
            throw new UserNotFoundException("Book is not borrowed");
        }
        rentRepository.deleteById(rentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
