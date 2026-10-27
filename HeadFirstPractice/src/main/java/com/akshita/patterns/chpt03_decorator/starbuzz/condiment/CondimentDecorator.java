package com.akshita.patterns.chpt03_decorator.starbuzz.condiment;

import com.akshita.patterns.chpt03_decorator.starbuzz.drinks.Beverage;

import java.util.Objects;

/**
 * IS-A Beverage (so it can stand in anywhere a drink can)
 * HAS-A Beverage (the thing it's wrapping).
 *
 * getDescription() is re-declared abstract on purpose: if it weren't, every
 * decorator would inherit Beverage's version and report "Unknown Beverage",
 * ignoring the drink inside. Making it abstract forces each decorator to
 * say: "whatever I wrap, plus me".
 */
public abstract class  CondimentDecorator extends Beverage {

    protected final Beverage beverage;
    public CondimentDecorator(Beverage beverage) {
        this.beverage = Objects.requireNonNull(beverage,"Can't decorate thin air");
    }

    @Override
    public abstract String getDescription();

}
