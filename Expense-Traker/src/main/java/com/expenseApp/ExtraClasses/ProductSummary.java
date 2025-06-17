package com.expenseApp.ExtraClasses;


import java.io.Serializable;

public class ProductSummary implements Serializable {
    private String latestProductName;
    private Integer totalSpent;

    public ProductSummary(String latestProductName, Integer totalSpent) {
        this.latestProductName = latestProductName;
        this.totalSpent = totalSpent;
    }

    public String getLatestProductName() {
        return latestProductName;
    }

    public Integer getTotalSpent() {
        return totalSpent;
    }
}
