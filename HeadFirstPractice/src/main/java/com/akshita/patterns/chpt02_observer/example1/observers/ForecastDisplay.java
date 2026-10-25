package com.akshita.patterns.chpt02_observer.example1.observers;

import com.akshita.patterns.chpt02_observer.example1.DisplayElement;
import com.akshita.patterns.chpt02_observer.example1.Subject;

public class ForecastDisplay implements Observer, DisplayElement {
    private float currentPressure = 29.92f; // a perfectly average day, for a first comparison
    private float lastPressure;

    public ForecastDisplay(Subject weatherData){
        weatherData.registerObserver(this);
    }
    @Override
    public void display() {
        System.out.print("Forecast: ");
        if (currentPressure > lastPressure) {
            System.out.println("Improving weather on the way!");
        } else if (currentPressure == lastPressure) {
            System.out.println("More of the same");
        } else {
            System.out.println("Watch out for cooler, rainy weather");
        }
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;
        display();
    }
}
