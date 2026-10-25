package com.akshita.patterns.chpt01_strategy.navigation.routes;

import java.util.List;

@FunctionalInterface
public interface RouteStrategy {
    List<String> buildRoute(String origin, String destination);
}
