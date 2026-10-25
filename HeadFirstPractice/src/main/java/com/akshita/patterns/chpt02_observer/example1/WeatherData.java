package com.akshita.patterns.chpt02_observer.example1;

import com.akshita.patterns.chpt02_observer.example1.observers.Observer;

import java.util.ArrayList;

public class WeatherData implements Subject {

   private ArrayList<Observer> observers = new ArrayList<>();
   private float temperature;
   private float humidity;
   private float pressure;


    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {

        for (Observer o : observers) {
            o.update(temperature, humidity, pressure);
        }

    }

    /** Called whenever the weather station delivers fresh data. */
    public void measurementsChanged() {
        notifyObservers();
    }

    /** Called whenever the weather station delivers fresh data. */
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }
    public float getPressure() {
        return pressure;
    }
}
