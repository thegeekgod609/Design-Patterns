package com.akshita.patterns.chpt02_observer.example1;


import com.akshita.patterns.chpt02_observer.example1.observers.Observer;

/** The thing being watched. Knows observers only as "something with an update()". */
public interface Subject {

    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
