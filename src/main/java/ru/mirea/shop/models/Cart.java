package ru.mirea.shop.models;

import ru.mirea.shop.entities.Item;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> cartItems;
    private double total;

    public Cart() {
        cartItems = new ArrayList<>();
        total = 0;
    }

    public CartItem getCartItem(Item item){
        for (CartItem cartItem : cartItems){
            if(cartItem.getItem().getId() == item.getId()){
                return cartItem;
            }
        }
        return null;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public int getItemCount(){
        return cartItems.size();
    }

    public void addItem(CartItem cartItem){
        addItem(cartItem.getItem(), cartItem.getQuantity());
    }

    public void addItem(Item item, int quantity){
        CartItem cartItem = getCartItem(item);

        if (cartItem != null){
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem(item);
            cartItem.setQuantity(quantity);
            cartItems.add(cartItem);
        }
    }

    public void updateItem(Item product, int quantity){ // throws ProductNotFoundException
        CartItem item = getCartItem(product);

        if (item != null){
            item.setQuantity(quantity);
        } else {
            // throw new ProductNotFoundException();
        }
    }

    public void removeItem(Item item){ // throws ProductNotFoundException
        CartItem cartItem = getCartItem(item);

        if (cartItem != null){
            cartItems.remove(cartItem);
        } else {
            // throw new ProductNotFoundException();
        }
    }

    public void clear(){
        cartItems.clear();
        total = 0;
    }

    public boolean isEmpty(){
        return cartItems.isEmpty();
    }

    public double getTotal(){
        total = 0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getSubTotal();
        }
        return total;
    }
}
