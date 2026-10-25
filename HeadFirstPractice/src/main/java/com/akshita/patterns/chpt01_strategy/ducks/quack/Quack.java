package com.akshita.patterns.chpt01_strategy.ducks.quack;

public class Quack implements QuackBehavior {
    @Override
    public String quack() {
        return "quacks";
    }
}
