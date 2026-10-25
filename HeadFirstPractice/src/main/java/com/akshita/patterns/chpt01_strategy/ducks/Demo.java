package com.akshita.patterns.chpt01_strategy.ducks;

import com.akshita.patterns.chpt01_strategy.ducks.fly.FlyRocketPowered;

public class Demo {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        System.out.println(mallard.describe());

        Duck model = new ModelDuck();
        System.out.println(model.display() + ": " + model.performFly());

        // The money shot: new behaviour at runtime, zero changes to ModelDuck.
        model.setFlyBehavior(new FlyRocketPowered());
        System.out.println(model.display() + ", after the rocket upgrade: " + model.performFly());

        // Shareholder-meeting insurance policy.
        Duck rubber = new RubberDuck();
        System.out.println(rubber.describe());
    }
}
