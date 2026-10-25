package com.akshita.patterns.chpt01_strategy.ducks.fly;

public class FlyWithWings implements FlyBehavior {
    @Override
    public String fly() {
        return "flies with wings";
    }
}
