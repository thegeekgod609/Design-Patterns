package com.akshita.patterns.chpt02_observer.example1.observers;

import com.akshita.patterns.chpt02_observer.example1.DisplayElement;
import com.akshita.patterns.chpt02_observer.example1.Subject;

public class StatisticsDisplay implements Observer, DisplayElement {

    private float sum;
    private int numReadings;
    private float max = Float.NEGATIVE_INFINITY;
    private float min = Float.POSITIVE_INFINITY;

    public StatisticsDisplay(Subject weatherdata) {
        weatherdata.registerObserver(this);
    }
    @Override
    public void display() {
        System.out.println("Avg/Max/Min temperature = " + (sum / numReadings) + "/" + max + "/" + min);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        sum += temperature;
        numReadings++;
        max = Math.max(max, temperature);
        min = Math.min(min, temperature);
        display();

    }
}
