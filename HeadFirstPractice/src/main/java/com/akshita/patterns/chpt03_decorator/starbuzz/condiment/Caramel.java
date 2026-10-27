package com.akshita.patterns.chpt03_decorator.starbuzz.condiment;


import com.akshita.patterns.chpt03_decorator.starbuzz.drinks.Beverage;

public class Caramel extends CondimentDecorator {

    public Caramel(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Caramel";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.25;
    }
}
