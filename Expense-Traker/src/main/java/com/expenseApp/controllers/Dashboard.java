package com.expenseApp.controllers;


import com.expenseApp.entities.Product;
import com.expenseApp.entities.User;
import com.expenseApp.services.ProductService;
import com.expenseApp.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.collection.spi.PersistentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class Dashboard {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;





    @RequestMapping("/home")
    public String goHome(HttpSession session ,Model model)
    {

        if (session.getAttribute("mob") != null) {
            return "redirect:/PassData";
        }
        // Else, go to landing/index page
        return "index";

    }

    @RequestMapping("/DisplayHistory")
    public String DisplayHistory(HttpSession session,Model model)
    {

        if (session.getAttribute("mob") != null) {

            int userId =(int) session.getAttribute("userId");

            List<Product> products = productService.getProductsByUserId(userId);

            model.addAttribute("productList",products);
            return "History";
        }
        // Else, go to landing/index page
        return "index";

    }

    @RequestMapping("/Profile")
    public String OpenProfile(HttpSession session,Model model)
    {

        if (session.getAttribute("mob") != null) {

            int userId =(int) session.getAttribute("userId");

            Optional<User> user=userService.getUserById(userId);
            model.addAttribute("user",user);
            if (user.isPresent()) {
                model.addAttribute("user", user.get()); // pass actual User
                return "Profile";
            } else {
                return "error";
            }

        }
        // Else, go to landing/index page
        return "redirect:/ShowError";

    }

    @RequestMapping("/ReturnIndex")
    public String ReturnIndex()
    {
        return "index";
    }

    @RequestMapping("/ShowError")
   public String shoeError()
    {
        return "error";
    }



}







