package com.akshita.patterns.chpt01_strategy.navigation.routes;

import java.util.List;

public class PublicTransportStrategy implements RouteStrategy {
    @Override
    public List<String> buildRoute(String origin, String destination) {
        return List.of(origin, "Bus stop", "Metro", destination);
    }
}
