package com.akshita.patterns.chpt01_strategy.ducks;

import com.akshita.patterns.chpt01_strategy.ducks.fly.FlyWithWings;
import com.akshita.patterns.chpt01_strategy.ducks.quack.Quack;

public class MallardDuck extends Duck {
    public MallardDuck() {
        super(new FlyWithWings(), new Quack());
    }

    @Override
    public String display() {
        return "Mallard";
    }
}
