package com.akshita.patterns.chpt03_decorator.starbuzz;

import com.akshita.patterns.chpt03_decorator.starbuzz.condiment.*;
import com.akshita.patterns.chpt03_decorator.starbuzz.drinks.*;

public class Demo {
    public static void main(String[] args) {
        Beverage espresso = new Espresso();
        print(espresso);

        Beverage darkRoast = new DarkRoast();
        darkRoast = new Mocha(darkRoast);
        darkRoast = new Mocha(darkRoast);   // double mocha: just wrap twice
        darkRoast = new Whip(darkRoast);
        print(darkRoast);

        Beverage houseBlend = new Whip(new Mocha(new Soy(new HouseBlend())));
        print(houseBlend);

        // Caramel was added without touching a single existing class.
        Beverage decaf = new Caramel(new Milk(new Decaf()));
        print(decaf);
    }

    private static void print(Beverage b) {
        System.out.printf("%s $%.2f%n", b.getDescription(), b.cost());
    }
}