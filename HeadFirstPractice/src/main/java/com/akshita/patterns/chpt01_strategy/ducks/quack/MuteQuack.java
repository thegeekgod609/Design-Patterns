package com.akshita.patterns.chpt01_strategy.ducks.quack;

public class MuteQuack implements QuackBehavior {
    @Override
    public String quack() {
        return "stays silent";
    }
}
