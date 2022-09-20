package com.example.experiment.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class RestaurantMenuItem {

    private String name;
    private String description;
    private Integer price;
}
