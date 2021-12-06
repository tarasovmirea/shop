package ru.mirea.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.shop.entities.Role;
import ru.mirea.shop.entities.User;
import ru.mirea.shop.repositories.UserRepository;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "users/userList";
    }
    @GetMapping("/{user}")
    public String editUser(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "/users/edit";
    }

    @PostMapping
    public String saveUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String address,
            @RequestParam String phone,
            @RequestParam Map<String, String> form,
            @RequestParam ("userId") User user) {

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhone(phone);

        Set<String> roles = Arrays.stream(Role.values()).map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for(String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);

        return "redirect:/users";
    }

}
