package com.akshita.patterns.chpt01_strategy.ducks.quack;

public class Squeak implements QuackBehavior {
    @Override
    public String quack() {
        return "squeaks";
    }
}
