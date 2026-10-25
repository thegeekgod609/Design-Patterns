package com.akshita.patterns.chpt01_strategy.ducks;

import com.akshita.patterns.chpt01_strategy.ducks.fly.FlyRocketPowered;
import com.akshita.patterns.chpt01_strategy.ducks.quack.Quack;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DuckTest {

    @Test
    public void duckDelegatesFlyToItsStrategy() {
        AtomicInteger calls = new AtomicInteger();
        Duck duck = new ModelDuck();
        duck.setFlyBehavior(() -> {
            calls.incrementAndGet();
            return "fake flight";
        });

        assertEquals("fake flight", duck.performFly());
        assertEquals(1, calls.get());
    }
    @Test
    void duckDelegatesQuackToItsStrategy() {
        AtomicInteger calls = new AtomicInteger();
        Duck duck = new MallardDuck();
        duck.setQuackBehavior(() -> {
            calls.incrementAndGet();
            return "fake quack";
        });

        assertEquals("fake quack", duck.performQuack());
        assertEquals(1, calls.get());
    }

    @Test
    void modelDuckStartsGroundedThenFliesAfterRocketUpgrade() {
        Duck model = new ModelDuck();
        assertEquals("can't fly", model.performFly());

        model.setFlyBehavior(new FlyRocketPowered());
        assertEquals("flies like a rocket", model.performFly());
    }

    @Test
    void rubberDuckNeverFliesByAccident() {
        Duck rubber = new RubberDuck();
        assertEquals("can't fly", rubber.performFly());
        assertEquals("squeaks", rubber.performQuack());
    }

    @Test
    void behavioursAreIndependentOfSpecies() {
        Duck decoy = new DecoyDuck();
        decoy.setQuackBehavior(new Quack());
        assertEquals("quacks", decoy.performQuack());
        assertEquals("can't fly", decoy.performFly());
    }

    @Test
    void nullStrategiesAreRejected() {
        Duck duck = new MallardDuck();
        assertThrows(NullPointerException.class, () -> duck.setFlyBehavior(null));
        assertThrows(NullPointerException.class, () -> duck.setQuackBehavior(null));
    }

    @Test
    void mallardDescribesItself() {
        assertEquals("Mallard: quacks, flies with wings", new MallardDuck().describe());
    }
}
