package com.expenseApp.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    private Long totalBudget;
    private Long spent;
    private Long remaining;
    private LocalDate startDate;
    private LocalDate endDate;
    private int days;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private List<Product> products;


    public Budget(int id, int userId, Long totalBudget, Long spent, Long remaining, LocalDate startDate, LocalDate endDate, int days, List<Product> products) {
        this.id = id;
        this.userId = userId;
        this.totalBudget = totalBudget;
        this.spent = spent;
        this.remaining = remaining;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.products = products;
    }

    public Budget() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Long totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Long getSpent() {
        return spent;
    }

    public void setSpent(Long spent) {
        this.spent = spent;
    }

    public Long getRemaining() {
        return remaining;
    }

    public void setRemaining(Long remaining) {
        this.remaining = remaining;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
