package com.example.experiment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItem {
    private String name;
    private Integer quantity;
    private Integer price;
}
