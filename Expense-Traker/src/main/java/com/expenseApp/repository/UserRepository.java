package com.expenseApp.repository;

import com.expenseApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByMobAndPassword(String mob, String password);


}
