package com.akshita.patterns.chpt02_observer.example1;

import com.akshita.patterns.chpt02_observer.example1.observers.CurrentConditionsDisplay;
import com.akshita.patterns.chpt02_observer.example1.observers.ForecastDisplay;
import com.akshita.patterns.chpt02_observer.example1.observers.HeatIndexDisplay;
import com.akshita.patterns.chpt02_observer.example1.observers.StatisticsDisplay;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        new CurrentConditionsDisplay(weatherData);
        new StatisticsDisplay(weatherData);
        ForecastDisplay forecast = new ForecastDisplay(weatherData);
        new HeatIndexDisplay(weatherData);

        System.out.println("--- Round 1 ---");
        weatherData.setMeasurements(80, 65, 30.4f);

        System.out.println("\n--- Round 2 ---");
        weatherData.setMeasurements(82, 70, 29.2f);

        // The forecaster quits mid-run. Everyone else carries on.
        weatherData.removeObserver(forecast);

        System.out.println("\n--- Round 3 (forecast has left the building) ---");
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
