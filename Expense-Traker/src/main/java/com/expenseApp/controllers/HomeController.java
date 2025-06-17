package com.expenseApp.controllers;

import com.expenseApp.ExtraClasses.ProductSummary;
import com.expenseApp.entities.Budget;
import com.expenseApp.services.BudgetService;
import com.expenseApp.services.ProductService;
import jakarta.persistence.Column;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private ProductService productService;


    @RequestMapping("/")
    public String home(HttpSession session, Model model)
    {
        if (session.getAttribute("mob") != null) {

            int userId = (int) session.getAttribute("userId");
            LocalDate today = LocalDate.now();

            Optional<Budget> currentBudget = budgetService.findCurrentBudget(userId, today);

//            if (currentBudget.isPresent()) {
//                int budgetId = currentBudget.get().getId();
//                Object[] result = productService.summary("Food", budgetId);
//
//                if (result != null && result.length >= 2) {
//                    String latestProductName = result[0] != null ? result[0].toString() : "N/A";
//                    int totalSpent = result[1] != null ? ((Number) result[1]).intValue() : 0;
//
//                    ProductSummary summary = new ProductSummary(latestProductName, totalSpent);
//                    model.addAttribute("summary", summary);
//                    return "home";
//                }
//            }
            return "redirect:/PassData";

        }

        // Else, go to landing/index page
        return "index";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    @RequestMapping("/register")
    public String register()
    {
        return "register";
    }


}
