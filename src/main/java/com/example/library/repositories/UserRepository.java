package com.example.library.repositories;

import com.example.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);
    List<User> findByEmailAndPassword(String email, String password);

}
