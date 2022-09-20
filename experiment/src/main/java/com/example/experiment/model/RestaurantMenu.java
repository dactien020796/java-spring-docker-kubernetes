package com.example.experiment.model;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMenu {

    private List<RestaurantMenuItem> items;

    public RestaurantMenu() {
        items = new ArrayList<>();
    }
    public void add(RestaurantMenuItem item) {
        if (doesItemExist(item)) {
            throw new IllegalArgumentException("Duplicate item");
        }
        items.add(item);
    }

    public boolean doesItemExist(RestaurantMenuItem item) {
        return items.contains(item);
    }
}
