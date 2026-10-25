package com.akshita.patterns.chpt01_strategy.ducks;

import com.akshita.patterns.chpt01_strategy.ducks.fly.FlyNoWay;
import com.akshita.patterns.chpt01_strategy.ducks.quack.MuteQuack;

public class DecoyDuck extends Duck {

    public DecoyDuck() {
        super(new FlyNoWay(),new MuteQuack());
    }

    @Override
    public String display() {
        return "DecoyDuck";
    }
}
