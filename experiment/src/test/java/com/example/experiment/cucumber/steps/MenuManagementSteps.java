package com.example.experiment.cucumber.steps;

import com.example.experiment.model.RestaurantMenu;
import com.example.experiment.model.RestaurantMenuItem;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuManagementSteps {

    private RestaurantMenuItem item;
    private RestaurantMenu menu = new RestaurantMenu();
    private String actualErrorMessage;

    @Given("I have a menu item with name {string} and price ${int}")
    public void i_have_a_menu_item_with_name_and_price(String name, Integer price) {
        item = new RestaurantMenuItem(name, "This is a description",  price);
    }

    @When("I add this menu item")
    public void i_add_this_menu_item() {
        try {
            menu.add(item);
        } catch (Exception e) {
            actualErrorMessage = e.getMessage();
        }
    }

    @Then("Menu item with name {string} should be added")
    public void menu_item_with_name_should_be_added(String name) {
        boolean result = menu.doesItemExist(item);
        assertTrue(result);
    }

    @Then("Error message with value {string}")
    public void error_message_with_value(String errorMessage) {
        assertEquals(errorMessage, actualErrorMessage);
    }
}
