package com.akshita.patterns.chpt01_strategy.ducks;

import com.akshita.patterns.chpt01_strategy.ducks.fly.FlyBehavior;
import com.akshita.patterns.chpt01_strategy.ducks.quack.QuackBehavior;

import java.util.Objects;

/**
 * Context. Knows WHAT it needs (a fly strategy, a quack strategy),
 * not HOW they work. Everything behavioural is delegated.
 */
public abstract class Duck {

    private FlyBehavior flyBehavior;
    private QuackBehavior quackBehavior;

    public Duck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        setFlyBehavior(flyBehavior);
        setQuackBehavior(quackBehavior);
    }

    // Setters = runtime swapping. Null is rejected so a duck can never
    // end up with no strategy (use FlyNoWay / MuteQuack instead).
    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = Objects.requireNonNull(quackBehavior,"quackBehavior can't be null");
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior =  Objects.requireNonNull(flyBehavior, "flyBehavior can't be null");
    }

    public abstract String display();

    /** The one thing all ducks share, so it stays in the superclass. */
    public String swim(){
        return "All ducks float, even decoys!";
    }

    public String performFly(){
        return flyBehavior.fly();
    }
    public String performQuack(){
        return quackBehavior.quack();
    }

    public String describe(){
        return display() + ": " + performQuack() + ", " + performFly();
    }
}
