package com.akshita.patterns.chpt01_strategy.navigation;

import com.akshita.patterns.chpt01_strategy.navigation.routes.*;

public class Demo {
    public static void main(String[] args) {
        Navigator navigator = new Navigator(new RoadStrategy());
        System.out.println(navigator.navigate("ROAD", "Home", "Destination"));

        navigator.setRouteStrategy(new WalkingStrategy());          // user taps "Walk"
        System.out.println(navigator.navigate("WALKING", "Home", "Destination"));

        navigator.setRouteStrategy(new PublicTransportStrategy());  // user taps "Transit"
        System.out.println( navigator.navigate("PUBLIC TRANSIT", "Home", "Destination"));

        // --- The open/closed test: these two were added LAST. Nothing above was edited. ---
        navigator.setRouteStrategy(new CyclingStrategy());
        System.out.println(navigator.navigate("CYCLING", "Home", "Destination"));

        navigator.setRouteStrategy(new SightseeingStrategy());
        System.out.println(navigator.navigate("SIGHTSEEING", "Home", "Destination"));
    }
}
