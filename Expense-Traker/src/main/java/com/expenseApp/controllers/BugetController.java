package com.expenseApp.controllers;

import com.expenseApp.entities.Budget;
import com.expenseApp.services.BudgetService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Controller
public class BugetController {


    @Autowired
    private BudgetService budgetService;

    @RequestMapping("/budgetFormOpen")
    public String openBudgetForm(HttpSession session, Model model) {

        Integer userId = (Integer) session.getAttribute("userId");

        LocalDate today = LocalDate.now();
        Optional<Budget> currentBudget = this.budgetService.findCurrentBudget(userId, today);
        if (currentBudget.isPresent()) {

            model.addAttribute("budget",currentBudget.get());
            return "DisplayBudget";
        }
        else
        {
            model.addAttribute("new","Set your monthly budget");
            return "budget";
        }


    }

    @RequestMapping("/checkBudget")
    public String checkBudget(HttpSession session,Model model)
    {

        model.addAttribute("old","Once you add a new Budget Old one will be Stop");
        return "redirect:/budgetFormOpen";
    }

    @RequestMapping("/addBudget")
    public String addNewBudget(@ModelAttribute("budget") Budget budget, HttpSession session, RedirectAttributes redirectAttributes)
    {
        int userId=(int) session.getAttribute("userId");
        int days = 30;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(days);

        LocalDate today = LocalDate.now();





            budget.setStatus("ACTIVE");
            budget.setUserId(userId);
            budget.setStartDate(startDate);
            budget.setEndDate(endDate);
            budget.setDays(days);
            budget.setSpent(0L);
            budget.setRemaining(budget.getTotalBudget()-budget.getSpent());

            redirectAttributes.addFlashAttribute("success", "Budget created successfully.");
            budgetService.addBudget(budget);
        return "redirect:/PassData";
    }

    @RequestMapping("/InactivateBudget")
    public String inactivateBudget(HttpSession session) {

        int userId = (int) session.getAttribute("userId");
        LocalDate today = LocalDate.now();
        Optional<Budget> currentBudgetOpt = this.budgetService.findCurrentBudget(userId, today);

        if (currentBudgetOpt.isPresent()) {
            Budget inactivateBudget = currentBudgetOpt.get();
            inactivateBudget.setStatus("INACTIVATE");

            // ✅ Save the updated budget
            budgetService.addBudget(inactivateBudget);

            return "redirect:/checkBudget";
        }

        return "redirect:/budgetFormOpen";
    }





}
