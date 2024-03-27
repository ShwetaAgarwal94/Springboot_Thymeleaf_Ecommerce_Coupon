package com.project.ecommerce.controller;

import com.project.ecommerce.dto.Login;
import com.project.ecommerce.model.User;
import com.project.ecommerce.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import java.util.logging.*;

@Controller
public class CouponController {

    private UserRepository userRepository;

    public CouponController(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @GetMapping({"/register", "/signup"})
    public String registration(ModelMap model){
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping({"/register", "/signup"})
    public String registered(@ModelAttribute("user") @Valid User user, BindingResult result, ModelMap model){
        if (result.hasErrors()) {
            return "signup";
        }
        String email = user.getEmail();
        User userByEmail = userRepository.findByEmail(email);
        if (userByEmail!=null){
            model.addAttribute("message", "User already exist with this email");
            return "signup";
        } else {
            userRepository.save(user);
        }
        return "login";
    }

    @GetMapping("/login")
    public String loginForm(ModelMap model){
        Login user = new Login();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String loggedIn(@ModelAttribute("user") @Valid Login user, BindingResult result, ModelMap model){
        if (result.hasErrors()) {
            System.out.println(result);
            return "login";
        }
        String email = user.getEmail();
        String password = user.getPassword();
        User userByEmail = userRepository.findByEmail(email);
        String username = userByEmail.getUsername();
        user.setUsername(username);

        if(userByEmail != null) {
            if (password.equals(userByEmail.getPassword())) {
                return "/home";
            }
            else {
                model.addAttribute("message", "Invalid Email or Password");
            }
        }
        return "/login";
    }

    @GetMapping({"/", "/home"})
    public String home(ModelMap model) {
        Login user = new Login();
        model.addAttribute("user", user);
        return "home";
    }

    @GetMapping("/bestdeal")
    public String bestDeal(ModelMap model) {
        Login user = new Login();
        model.addAttribute("user", user);
        return "todaysDeal";
    }

    @GetMapping({"/food-coupon"})
    public String foodcoupon(ModelMap model) {
        Login user = new Login();
        model.addAttribute("user", user);
        return "foodCoupon";
    }

    @GetMapping({"/electronics-coupon"})
    public String electronicscoupon(ModelMap model) {
        Login user = new Login();
        model.addAttribute("user", user);
        return "electronicsCoupon";
    }

    @GetMapping({"/footwear-coupon"})
    public String footwearcoupon(ModelMap model) {
        Login user = new Login();
        model.addAttribute("user", user);
        return "footwearCoupon";
    }

    @GetMapping({"/addNew"})
    public String addNewCoupon(ModelMap model) {
        Login user = new Login();
        model.addAttribute("user", user);
        return "addNewCoupon";
    }

}
