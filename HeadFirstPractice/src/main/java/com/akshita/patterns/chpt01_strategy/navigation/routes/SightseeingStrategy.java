package com.akshita.patterns.chpt01_strategy.navigation.routes;

import java.util.List;

public class SightseeingStrategy implements RouteStrategy {

    @Override
    public List<String> buildRoute(String origin, String destination) {
        return List.of(origin, "Old Fort", "Museum", "Selfie spot", destination);
    }
}
