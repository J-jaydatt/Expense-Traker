    package com.expenseApp.controllers;

    import com.expenseApp.ExtraClasses.ProductSummary;
    import com.expenseApp.entities.Budget;
    import com.expenseApp.entities.Product;
    import com.expenseApp.entities.User;
    import com.expenseApp.services.BudgetService;
    import com.expenseApp.services.ProductService;
    import com.expenseApp.services.UserService;
    import jakarta.servlet.http.HttpSession;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.mvc.support.RedirectAttributes;

    import java.time.LocalDate;
    import java.time.LocalTime;
    import java.util.Arrays;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.Optional;

    @Controller
    public class ProductController {

        @Autowired
        private UserService userService;

        @Autowired
        private ProductService productService;

        @Autowired
        private BudgetService budgetService;

        //    1. Opeing the puchase form for food travel etc.
        @PostMapping("/add")
        public String addExpense(@RequestParam("item") String item, Model model) {
            model.addAttribute("item", item);
            return "AddExpense";
        }

        @PostMapping("/addItem")
        public String addItem(@ModelAttribute("product") Product product, HttpSession session, Model model, RedirectAttributes redirectAttributes) {

            System.out.println("Inside homePage controller method");


            int userId = (int) session.getAttribute("userId");
            Optional<User> user = userService.GetByUserId(userId);

            user.ifPresent(product::setUser);

            LocalDate today = LocalDate.now();
            LocalTime time = LocalTime.now();
            product.setDate(today);
            product.setTime(time);

            Optional<Budget> currentBudget = budgetService.findCurrentBudget(userId, today);

                   // price of the product


                if (currentBudget.isPresent()) {
                    Budget budget = currentBudget.get();

                    Long remaining = budget.getRemaining();
                    Long price = product.getPrice();

                    if (price <= remaining) {
                        // Update budget
                        budget.setSpent(budget.getSpent() + price);
                        budget.setRemaining(remaining - price);
                        product.setBudget(budget);

                        budgetService.addBudget(budget);
                        userService.addExpense(product);

                        int budgetId = budget.getId();

                        // Categories you want to show summary for
                        String[] categories = {"Food", "Travel", "Needs", "Wishes" ,"Others"};

                        Map<String, ProductSummary> summaryMap = new HashMap<>();

                        for (String category : categories) {
                            Object[] result = productService.summary(category, budgetId);

                            String latestProductName = result[0] != null ? result[0].toString() : "N/A";
                            int totalSpent = result[1] != null ? ((Number) result[1]).intValue() : 0;

                            summaryMap.put(category, new ProductSummary(latestProductName, totalSpent));
                        }

                        // Pass entire map as flash attribute
                        redirectAttributes.addFlashAttribute("summaryMap", summaryMap);
                        return "redirect:/PassData";
                    }
                    else {
                        redirectAttributes.addFlashAttribute("error", "Out of Budget.");
                        return "redirect:/PassData";
                    }
                }
             else {
                redirectAttributes.addFlashAttribute("error", "No active budget found.");
                return "redirect:/PassData";
            }
//            return "home";
        }
    }


