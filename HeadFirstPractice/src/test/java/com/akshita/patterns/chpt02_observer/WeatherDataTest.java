package com.akshita.patterns.chpt02_observer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.akshita.patterns.chpt02_observer.example1.WeatherData;
import com.akshita.patterns.chpt02_observer.example1.observers.HeatIndexDisplay;
import com.akshita.patterns.chpt02_observer.example1.observers.Observer;
import org.junit.jupiter.api.Test;

class WeatherDataTest {

    /** A fake observer: records what it was told, nothing else. */
    static class FakeObserver implements Observer {
        final List<float[]> received = new ArrayList<>();

        @Override
        public void update(float temperature, float humidity, float pressure) {
            received.add(new float[] {temperature, humidity, pressure});
        }
    }

    @Test
    void registeredObserverReceivesTheNewValues() {
        WeatherData data = new WeatherData();
        FakeObserver fake = new FakeObserver();
        data.registerObserver(fake);

        data.setMeasurements(80f, 65f, 30.4f);

        assertEquals(1, fake.received.size());
        assertEquals(80f, fake.received.getFirst()[0]);
        assertEquals(65f, fake.received.getFirst()[1]);
        assertEquals(30.4f, fake.received.getFirst()[2]);
    }

    @Test
    void everyObserverIsNotifiedOnEveryChange() {
        WeatherData data = new WeatherData();
        FakeObserver a = new FakeObserver();
        FakeObserver b = new FakeObserver();
        data.registerObserver(a);
        data.registerObserver(b);

        data.setMeasurements(80f, 65f, 30.4f);
        data.setMeasurements(82f, 70f, 29.2f);

        assertEquals(2, a.received.size());
        assertEquals(2, b.received.size());
    }

    @Test
    void removedObserverStopsReceivingUpdates() {
        WeatherData data = new WeatherData();
        FakeObserver fake = new FakeObserver();
        data.registerObserver(fake);
        data.setMeasurements(80f, 65f, 30.4f);

        data.removeObserver(fake);
        data.setMeasurements(82f, 70f, 29.2f);

        assertEquals(1, fake.received.size());
    }

    @Test
    void heatIndexMatchesKnownValue() {
        // 80F at 65% humidity feels like roughly 82.4F with the NWS regression.
        assertEquals(82.4f, HeatIndexDisplay.computeHeatIndex(80f, 65f), 0.1f);
    }
}