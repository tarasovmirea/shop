package ru.mirea.shop.models;

import ru.mirea.shop.entities.Item;

public class CartItem {
    private final Item item;
    private int quantity;
    private double subTotal;

    public CartItem(Item item) {
        this.item = item;
        this.quantity = 1;
        this.subTotal = item.getPrice();
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        subTotal = item.getPrice() * quantity;
        return subTotal;
    }
}
