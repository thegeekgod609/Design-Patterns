package com.akshita.patterns.chpt03_decorator.starbuzz.drinks;

public abstract class Beverage {

    protected String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();

}
