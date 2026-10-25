package com.akshita.patterns.chpt02_observer.example1.observers;

import com.akshita.patterns.chpt02_observer.example1.DisplayElement;
import com.akshita.patterns.chpt02_observer.example1.Subject;

import java.util.Locale;

public class HeatIndexDisplay implements Observer, DisplayElement {

    private float heatIndex;

    public HeatIndexDisplay(Subject weatherData) {
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println(String.format(Locale.ROOT, "Heat index is %.2f", heatIndex));
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.heatIndex = computeHeatIndex(temperature, humidity);
        display();
    }

    /** NWS Rothfusz regression: temperature in F, relative humidity in %. A stand-in; the formula isn't the lesson. */
    public static float computeHeatIndex(float t, float rh) {
        return (float) (-42.379
                + 2.04901523 * t
                + 10.14333127 * rh
                - 0.22475541 * t * rh
                - 0.00683783 * t * t
                - 0.05481717 * rh * rh
                + 0.00122874 * t * t * rh
                + 0.00085282 * t * rh * rh
                - 0.00000199 * t * t * rh * rh);
    }

}
