package com.akshita.patterns.chpt03_decorator.starbuzz.drinks;

public class DarkRoast extends Beverage {

    public DarkRoast(){
        description = "Dark Roast Coffee";
    }
    @Override
    public double cost() {
        return 0.99 ;
    }
}
