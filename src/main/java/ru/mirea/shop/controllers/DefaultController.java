package ru.mirea.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.shop.entities.Item;
import ru.mirea.shop.entities.Role;
import ru.mirea.shop.entities.User;
import ru.mirea.shop.repositories.ItemRepository;
import ru.mirea.shop.repositories.UserRepository;

@Controller
public class DefaultController {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index(){
        return "home";
    }

    @GetMapping("/catalog")
    public String catalog(Model model){
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "catalog";
    }

    @GetMapping("/cart")
    public String cart(Model model){
        return "cart";
    }

    @GetMapping("/contacts")
    public String contacts(){
        return "contacts";
    }

    @GetMapping("/{user}")
    public String editUser(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "profile";
    }
    @PostMapping("/profile")
    public String saveUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String address,
            @RequestParam String phone,
            @RequestParam ("userId") User user) {
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhone(phone);

        userRepository.save(user);

        return "redirect:/";
    }
}