package com.akshita.patterns.chpt02_observer.example1.observers;

import java.util.Observable;

/** Anyone who wants to be told about new weather. Push style: values come to you. */
public interface Observer {
    void update(float temperature, float humidity, float pressure);
}
