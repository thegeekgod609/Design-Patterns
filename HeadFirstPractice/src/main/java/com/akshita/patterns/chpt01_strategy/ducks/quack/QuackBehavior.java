package com.akshita.patterns.chpt01_strategy.ducks.quack;

@FunctionalInterface
public interface QuackBehavior {
    String quack();
}
