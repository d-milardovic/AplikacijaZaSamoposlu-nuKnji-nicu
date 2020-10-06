package com.example.library.repositories;

import com.example.library.entity.Rent;
import com.example.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent, Integer> {


    public List<Rent> findByUserId(Integer userId);
    public List<Rent> findByUser(Optional<User> user);

}
