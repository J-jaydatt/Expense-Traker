package com.expenseApp.services;

import com.expenseApp.entities.Product;
import com.expenseApp.entities.User;
import com.expenseApp.repository.ProductRepository;
import com.expenseApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public User saveUser(User user)
    {
           User user2= this.userRepository.save(user);
           return user2;
    }

    public Optional<User> login(String mob , String password)
    {
        return this.userRepository.findByMobAndPassword(mob,password);

    }
    public Optional<User> GetByUserId(int id)
    {
        return this.userRepository.findById(id);

    }

    public void   addExpense(Product product)
    {
        this.productRepository.save(product);
    }

    public Optional<User> getUserById(int id)
    {
        return userRepository.findById(id);
    }
}
