package ru.mirea.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mirea.shop.entities.Role;
import ru.mirea.shop.entities.User;
import ru.mirea.shop.repositories.UserRepository;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){

        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb != null){
            model.addAttribute("message", "User exist!");
            return "registration";
        }

        if(userRepository.findAll() != null){
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);
        }
        else{
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.ADMIN));
            userRepository.save(user);
        }
        return "redirect:/login";
    }
}
