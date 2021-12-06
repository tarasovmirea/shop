package ru.mirea.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.shop.entities.Item;
import ru.mirea.shop.models.Cart;
import ru.mirea.shop.services.CartManager;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartManager cartManager;

    @RequestMapping("/add")
    public String add(HttpSession session, @RequestParam("id") Item item,
                      @RequestParam(value = "qty", required = false, defaultValue = "1") int qty){
        Cart cart = cartManager.getCart(session);
        cart.addItem(item, qty);
        return "cart";
    }

    @RequestMapping("/remove")
    public String remove(HttpSession session, @RequestParam("id") Item item){
        Cart cart = cartManager.getCart(session);
        cart.removeItem(item);
        return "cart";
    }

    @RequestMapping("/update")
    public String update(HttpSession session, @RequestParam("id") Item item, @RequestParam("qty") int qty){
        Cart cart = cartManager.getCart(session);
        cart.updateItem(item, qty);
        return "cart";
    }
}
