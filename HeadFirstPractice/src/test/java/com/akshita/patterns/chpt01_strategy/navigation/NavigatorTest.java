package com.akshita.patterns.chpt01_strategy.navigation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.akshita.patterns.chpt01_strategy.navigation.routes.RouteStrategy;
import org.junit.jupiter.api.Test;

class NavigatorTest {

    @Test
    void rendersWhateverCheckpointsTheStrategyReturns() {
        // A lambda as a fake strategy: no road/walk/transit logic needed to test the context.
        RouteStrategy fake = (from, to) -> List.of(from, "Teleporter", to);
        Navigator navigator = new Navigator(fake);

        assertEquals("Mode: FAKE -> A -> Teleporter -> B", navigator.navigate("FAKE", "A", "B"));
    }

    @Test
    void passesOriginAndDestinationThroughToTheStrategy() {
        String[] seen = new String[2];
        Navigator navigator = new Navigator((from, to) -> {
            seen[0] = from;
            seen[1] = to;
            return List.of(from, to);
        });

        navigator.navigate("X", "Home", "Office");

        assertArrayEquals(new String[] {"Home", "Office"}, seen);
    }

    @Test
    void strategyCanBeSwappedAtRuntime() {
        Navigator navigator = new Navigator((from, to) -> List.of("first"));
        assertEquals("Mode: M -> first", navigator.navigate("M", "A", "B"));

        navigator.setRouteStrategy((from, to) -> List.of("second"));
        assertEquals("Mode: M -> second", navigator.navigate("M", "A", "B"));
    }

    @Test
    void rejectsNullStrategy() {
        assertThrows(NullPointerException.class, () -> new Navigator(null));
    }
}