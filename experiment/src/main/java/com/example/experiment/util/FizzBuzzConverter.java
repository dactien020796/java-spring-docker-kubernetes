package com.example.experiment.util;

public class FizzBuzzConverter {

    public static final String FIZZ = "Fizz";
    public static final String BUZZ = "Buzz";
    public static final String FIZZ_BUZZ = "FizzBuzz";

    public String convert(int i) {
        if (i < 0 || i > 100) {
            throw new IllegalArgumentException("Value should not be a negative number and must be less than equals 100");
        }

        if (i % 15 == 0) {
            return FIZZ_BUZZ;
        } else if (i % 5 == 0) {
            return BUZZ;
        } else if (i % 3 == 0) {
            return FIZZ;
        }
        return String.valueOf(i);
    }
}
