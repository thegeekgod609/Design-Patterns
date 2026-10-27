package com.akshita.patterns.chpt03_decorator.starbuzz.drinks;

import static org.junit.jupiter.api.Assertions.*;

import com.akshita.patterns.chpt03_decorator.starbuzz.condiment.*;
import org.junit.jupiter.api.Test;

class BeverageTest {
    private static final double EPS = 0.001;

    @Test
    void plainEspressoIsUnaffectedByTheExistenceOfDecorators() {
        Beverage b = new Espresso();
        assertEquals("Espresso Coffee", b.getDescription());
        assertEquals(1.99, b.cost(), EPS);
    }

    @Test
    void totalIsBasePlusSumOfCondiments() {
        Beverage b = new Whip(new Mocha(new Mocha(new DarkRoast())));
        assertEquals(0.99 + 0.20 + 0.20 + 0.10, b.cost(), EPS);
    }

    @Test
    void descriptionListsEverythingInWrapOrder() {
        Beverage b = new Whip(new Mocha(new Mocha(new DarkRoast())));
        assertEquals("Dark Roast Coffee, Mocha, Mocha, Whip", b.getDescription());
    }

    @Test
    void wrapOrderShowsUpInDescriptionButNotInPrice() {
        Beverage a = new Soy(new Mocha(new HouseBlend()));
        Beverage c = new Mocha(new Soy(new HouseBlend()));
        assertEquals("House Blend Coffee, Mocha, Soy", a.getDescription());
        assertEquals("House Blend Coffee, Soy, Mocha", c.getDescription());
        assertEquals(a.cost(), c.cost(), EPS);
    }

    @Test
    void newCondimentNeedsNoChangesToExistingClasses() {
        Beverage b = new Caramel(new Decaf());
        assertEquals("Decaf Coffee, Caramel", b.getDescription());
        assertEquals(1.05 + 0.25, b.cost(), EPS);
    }

    @Test
    void decoratorRefusesToWrapNothing() {
        assertThrows(NullPointerException.class, () -> new Milk(null));
    }
}