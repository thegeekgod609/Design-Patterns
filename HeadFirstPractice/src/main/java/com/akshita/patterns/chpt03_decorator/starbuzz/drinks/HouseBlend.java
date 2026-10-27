package com.akshita.patterns.chpt03_decorator.starbuzz.drinks;

/**
 * This is the base element
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
