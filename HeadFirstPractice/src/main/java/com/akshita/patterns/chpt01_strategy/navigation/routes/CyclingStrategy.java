package com.akshita.patterns.chpt01_strategy.navigation.routes;

import java.util.List;

public class CyclingStrategy implements RouteStrategy {
    @Override
    public List<String> buildRoute(String origin, String destination) {
        return List.of(origin, "Bike lane", "Riverside path", destination);
    }
}
