package com.expenseApp.services;

import com.expenseApp.entities.Product;
import com.expenseApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    public Object[] summary(String type, int budgetId) {
        Object result = productRepository.getProductSummary(type, budgetId);

        if (result instanceof Object[] array) {
            return array;
        } else {
            return null;
        }
    }

    public List<Product> getProductsByUserId(int userId) {
        return productRepository.findByUserId(userId);
    }





}
