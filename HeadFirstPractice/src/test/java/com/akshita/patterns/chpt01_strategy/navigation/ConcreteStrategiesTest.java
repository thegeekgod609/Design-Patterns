package com.akshita.patterns.chpt01_strategy.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.akshita.patterns.chpt01_strategy.navigation.routes.PublicTransportStrategy;
import com.akshita.patterns.chpt01_strategy.navigation.routes.RoadStrategy;
import com.akshita.patterns.chpt01_strategy.navigation.routes.WalkingStrategy;
import org.junit.jupiter.api.Test;

class ConcreteStrategiesTest {

    @Test
    void roadGoesViaHighway() {
        assertEquals(List.of("A", "Highway", "B"), new RoadStrategy().buildRoute("A", "B"));
    }

    @Test
    void walkingGoesViaPark() {
        assertEquals(List.of("A", "Park", "B"), new WalkingStrategy().buildRoute("A", "B"));
    }

    @Test
    void transitGoesViaBusAndMetro() {
        assertEquals(List.of("A", "Bus stop", "Metro", "B"),
                new PublicTransportStrategy().buildRoute("A", "B"));
    }
}