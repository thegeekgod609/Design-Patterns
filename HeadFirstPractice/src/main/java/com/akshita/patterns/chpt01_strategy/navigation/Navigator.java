package com.akshita.patterns.chpt01_strategy.navigation;

import com.akshita.patterns.chpt01_strategy.navigation.routes.RouteStrategy;

import java.util.List;
import java.util.Objects;

/**
 * The Context. It knows NOTHING about roads, feet, or metros.
 * It holds a strategy, delegates to it, and draws whatever checkpoints come back.
 */
public class Navigator {

    /** HAS-A Relationship with the interface of the Strategy */
    private RouteStrategy routeStrategy;

    public Navigator(RouteStrategy routeStrategy) {
        setRouteStrategy(routeStrategy);
    }

    /** The runtime switch: this is what the UI button calls. */
    public void setRouteStrategy(RouteStrategy routeStrategy) {
        this.routeStrategy = Objects.requireNonNull(routeStrategy);
    }

    /** Asks the strategy for a route and renders it. Returns the rendered line (handy for tests). */
    public String navigate(String label, String origin, String destination) {
        List<String> checkpoints = routeStrategy.buildRoute(origin, destination);
        String rendered = "Mode: " + label + " -> " + String.join(" -> ", checkpoints);
        System.out.println(rendered);
        return rendered;
    }

}
