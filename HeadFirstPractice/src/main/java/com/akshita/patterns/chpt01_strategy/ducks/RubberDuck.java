package com.akshita.patterns.chpt01_strategy.ducks;

import com.akshita.patterns.chpt01_strategy.ducks.fly.FlyBehavior;
import com.akshita.patterns.chpt01_strategy.ducks.fly.FlyNoWay;
import com.akshita.patterns.chpt01_strategy.ducks.quack.QuackBehavior;
import com.akshita.patterns.chpt01_strategy.ducks.quack.Squeak;

public class RubberDuck extends Duck{

    public RubberDuck() {
        super(new FlyNoWay(), new Squeak());
    }

    @Override
    public String display() {
        return "RubberDuck";
    }
}
