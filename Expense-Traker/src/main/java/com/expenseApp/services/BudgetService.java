package com.expenseApp.services;


import com.expenseApp.entities.Budget;
import com.expenseApp.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class BudgetService {

        @Autowired
        private BudgetRepository budgetRepository;

    //Finding the current budget for display
    public Optional<Budget> findCurrentBudget(Integer userId, LocalDate today)
    {

            Optional<Budget> curentBudget= Optional.ofNullable(budgetRepository.findLatestActiveBudget(userId, today));
            return curentBudget;
    }

    public void addBudget(Budget budget)
    {
        budgetRepository.save(budget);
    }
}
