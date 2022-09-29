package com.example.experiment.cucumber.steps;

import com.example.experiment.util.FizzBuzzConverter;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FizzBuzzSteps {

    int value;
    String result;
    Exception exception;
    FizzBuzzConverter converter;

    public FizzBuzzSteps() {
        converter = new FizzBuzzConverter();
    }

    @Given("Value is {int}")
    public void value_is(Integer value) {
        this.value = value;
    }

    @When("FizzBuzz convert")
    public void fizz_buzz_convert() {
        try {
            result = converter.convert(value);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("{string} is returned")
    public void is_returned(String expectedResult) {
        assertEquals(expectedResult, result);
    }

    @Then("{string} exception thrown")
    public void exception_thrown(String expectedException) {
        assertEquals(expectedException, exception.getClass().getSimpleName());
    }

    @Then("Exception message contains {string}")
    public void message_contains(String expectedMessage) {
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
}
