package com.akshita.patterns.chpt01_strategy.ducks;

import com.akshita.patterns.chpt01_strategy.ducks.fly.FlyBehavior;
import com.akshita.patterns.chpt01_strategy.ducks.fly.FlyNoWay;
import com.akshita.patterns.chpt01_strategy.ducks.quack.MuteQuack;
import com.akshita.patterns.chpt01_strategy.ducks.quack.QuackBehavior;

public class ModelDuck extends Duck {
    public ModelDuck() {
        super(new FlyNoWay(), new MuteQuack());
    }

    @Override
    public String display() {
        return "ModelDuck";
    }
}
