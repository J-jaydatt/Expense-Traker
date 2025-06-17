package com.expenseApp.controllers;


import com.expenseApp.ExtraClasses.ProductSummary;
import com.expenseApp.entities.Budget;
import com.expenseApp.entities.User;
import com.expenseApp.repository.ProductRepository;
import com.expenseApp.services.BudgetService;
import com.expenseApp.services.ProductService;
import com.expenseApp.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class Login {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @RequestMapping("/regsiterUser")
    public String UserRegister(@ModelAttribute("user") User user, Model model)
    {
        User res=this.userService.saveUser(user);
        if(user!=null)
        {
            return "login";
        }
        else{
            model.addAttribute("msg","Failed to Register");
            return "register";

        }
    }
    @RequestMapping(path = "/userLogin",method = RequestMethod.POST)
    public String LoginUser(@RequestParam("mobile") String mob,
                            @RequestParam("password") String password ,HttpSession session ,Model model)
    {
           Optional<User> optionalUse= this.userService.login(mob,password );


           if(optionalUse.isPresent())
           {
               String name=optionalUse.get().getName();
               int id=optionalUse.get().getId();
               session.setAttribute("userId",id);
               session.setAttribute("mob", mob);
               session.setAttribute("name", name);//Seesion start
               return "redirect:/PassData";

           }
           else {
               model.addAttribute("msg", "Wrong id or Passord ");
               return "login";
           }
    }


    @RequestMapping("/PassData")
    public String passHomePageData(HttpSession session, Model model) {
        int userId = (int) session.getAttribute("userId");
        LocalDate today = LocalDate.now();
        Optional<Budget> budgetOpt = budgetService.findCurrentBudget(userId, today);

        Map<String, ProductSummary> summaryMap = new HashMap<>(); // ✅ Always initialized

        if (budgetOpt.isPresent()) {
            Budget budget = budgetOpt.get();
            int budgetId = budget.getId();
            String[] categories = {"Food", "Travel", "Needs", "Wishes", "Others"};

            for (String category : categories) {
                Object[] result = productService.summary(category, budgetId);

                String latestProductName = result[0] != null ? result[0].toString() : "N/A";
                int totalSpent = result[1] != null ? ((Number) result[1]).intValue() : 0;

                summaryMap.put(category, new ProductSummary(latestProductName, totalSpent));
            }

            model.addAttribute("budget", budget);

            List<Object[]> top3 = productRepository.getTop3FormattedProductsByBudgetId(budgetId,PageRequest.of(0, 3));
            model.addAttribute("top3products", top3);
        } else {
            model.addAttribute("budget", null);
        }

        model.addAttribute("summaryMap", summaryMap); // ✅ Always set
        return "home";
    }



    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // remove all session attributes
        return "index";
    }

}
