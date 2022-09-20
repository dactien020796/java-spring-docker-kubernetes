package com.example.experiment.cucumber.steps;

import com.example.experiment.model.Cart;
import com.example.experiment.model.CartItem;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataTableSteps {

    private static final String NAME_COLUMN = "Item name";
    private static final String QUANTITY_COLUMN = "Quantity";
    private static final String PRICE_COLUMN = "Price";

    private Cart cart;
    private Integer sum = 0;

    @Given("Buy following items")
    public void buy_following_items(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> cartTable = dataTable.asMaps(String.class, String.class);
        cart = new Cart();
        for (Map<String, String> billItems : cartTable) {
            cart.add(new CartItem(billItems.get(NAME_COLUMN), Integer.parseInt(billItems.get(QUANTITY_COLUMN)), Integer.parseInt(billItems.get(PRICE_COLUMN))));
        }
    }

    @When("Calculate bill")
    public void calculate_bill() {
        sum = cart.calculateBill();
    }

    @Then("a bill of ${int} should be generated")
    public void a_bill_of_$40_should_be_generated(Integer expectedAmount) {
        assertEquals(expectedAmount, sum);
    }

}
