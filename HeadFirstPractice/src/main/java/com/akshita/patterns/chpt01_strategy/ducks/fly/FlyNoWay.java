package com.akshita.patterns.chpt01_strategy.ducks.fly;

/** Null Object flavour: does nothing, but safely, so Duck never null-checks. */
public class FlyNoWay implements FlyBehavior {

    @Override
    public String fly() {
        return "can't fly";
    }
}
