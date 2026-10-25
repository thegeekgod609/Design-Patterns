package com.akshita.patterns.chpt01_strategy.navigation;

import java.util.List;

/**
 * Example of Bad Code: "The Monster". One class, one if/else ladder, zero self-respect.
 * Every new transport mode means cracking this class open again.
 */
public class UglyNavigator {

    public enum Mode { ROAD, WALK, TRANSIT }

    public void buildRoute(Mode mode, String origin, String destination) {
        List<String> checkpoints;

        if (mode == Mode.ROAD) {
            checkpoints = List.of(origin, "Highway", destination);          // imagine 200 lines here
        } else if (mode == Mode.WALK) {
            checkpoints = List.of(origin, "Park", destination);             // ...and 150 here
        } else if (mode == Mode.TRANSIT) {
            checkpoints = List.of(origin, "Bus stop", "Metro", destination); // ...and 300 of timetables
        } else {
            throw new IllegalArgumentException("Unknown mode: " + mode);
        }

        // Drawing code lives in the same class. Because why not mix concerns? (Because pain, that's why.)
        System.out.println("Mode: " + mode + " -> " + String.join(" -> ", checkpoints));
    }

    public static void main(String[] args) {
        UglyNavigator nav = new UglyNavigator();
        nav.buildRoute(Mode.ROAD, "Home", "Destination");
        nav.buildRoute(Mode.WALK, "Home", "Destination");
        nav.buildRoute(Mode.TRANSIT, "Home", "Destination");
    }
}