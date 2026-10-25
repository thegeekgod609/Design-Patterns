package com.akshita.patterns.chpt01_strategy.ducks.fly;

public class FlyRocketPowered implements FlyBehavior {
    @Override
    public String fly() {
        return  "flies like a rocket";
    }
}
