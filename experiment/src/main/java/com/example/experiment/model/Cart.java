package com.example.experiment.model;

import java.util.ArrayList;
import java.util.List;
public class Cart {

    private final List<CartItem> items = new ArrayList<>();

    public void add(CartItem cartItem) {
        items.add(cartItem);
    }

    public Integer calculateBill() {
        return items.stream()
            .map(i -> i.getPrice() * i.getQuantity())
            .reduce(0, Integer::sum);
    }
}
