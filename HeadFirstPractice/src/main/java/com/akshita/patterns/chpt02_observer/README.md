# Observer Pattern: The Weather-O-Rama Weather Station

**Source:** *Head First Design Patterns*, Chapter 2 (Keeping your Objects in the Know)
**Pattern:** Observer (Behavioral)

---

## The problem

Your team has just won a contract to build the next generation of **Weather-O-Rama's Internet-based weather monitoring station**. Three players are involved:

- the **weather station**, the physical device that collects real weather data;
- the **`WeatherData` object**, which tracks the incoming data (temperature, humidity, barometric pressure);
- the **displays**, which show the weather to users.

Weather-O-Rama supplies `WeatherData`. Your job is to build the application that shows the weather. `WeatherData` offers:

- getters for **temperature**, **humidity** and **pressure**;
- a method that gets called **whenever new measurements arrive**. You don't know or care how it's called, only that it is.

### The requirements

Build three display elements that update every time new measurements come in:

1. **Current conditions:** the latest temperature, humidity and pressure.
2. **Statistics:** average, minimum and maximum temperature.
3. **Forecast:** a simple prediction based on pressure changes.

And the part that makes this a design problem, not a coding task:

> **The system must be expandable.** Other developers should be able to write their own display elements, and users should be able to **add or remove** as many displays as they like, **at runtime**.

Weather-O-Rama also plans to charge per display, so more displays means more money, and everyone wants an easy plug-in story.

### The tempting first attempt

The obvious implementation: inside the "measurements changed" method, read the three values, then call an update method on each display, one by one, passing the values.

Why this hurts:

| Problem | Consequence |
|---------|-------------|
| Code is written against **concrete display classes** | Every new display means editing `WeatherData`. |
| Displays can't be added or removed **at runtime** | The list is frozen at compile time. |
| The part that changes (the set of displays) is **mixed in** with the part that doesn't (receiving measurements) | Violates "encapsulate what varies." |
| Other developers can't plug in anything | The API story is dead on arrival. |

---

## The goal

Redesign so that:

1. `WeatherData` (the **subject**) maintains a list of interested parties and notifies them when data changes. It knows nothing about concrete displays.
2. Every display (an **observer**) implements one common interface, so the subject can talk to all of them uniformly.
3. Displays can **register and unregister at runtime**.
4. Adding a new display requires **no change** to `WeatherData`.

The pattern's official definition, which you should be able to recite and rephrase: **a one-to-many dependency between objects, so that when one object changes state, all its dependents are notified and updated automatically.**

Principle to chase: *strive for loosely coupled designs between objects that interact.*

---

## What to build

Package: `com.akshita.patterns.ch02_observer.weather`

| Piece | Role |
|-------|------|
| `Subject` | Interface: register an observer, remove an observer, notify observers |
| `Observer` | Interface: an update method that receives the new measurements |
| `DisplayElement` | Interface: a display method, so every display can show itself |
| `WeatherData` | Concrete subject. Keeps its observers and the three measurements. |
| `CurrentConditionsDisplay` | Observer + display |
| `StatisticsDisplay` | Observer + display, tracks average, min and max |
| `ForecastDisplay` | Observer + display, compares current and last pressure |
| `WeatherStation` (or `Demo`) | Creates the subject, creates displays, feeds fake measurements |

Displays should **register themselves** with the subject when constructed, and the subject should hand them the new values on each update (the "push" style).

### Checklist

- [ ] First, write the tempting version with hardcoded calls to each display, and commit it.
- [ ] Refactor to Observer and commit again.
- [ ] Feed at least three rounds of fake measurements and see all three displays update each time.
- [ ] **Unregister** one display mid-run and show that it stops updating.
- [ ] `WeatherData` contains no `import` of any concrete display.
- [ ] Tests: use a fake observer, push a measurement, and assert it received the right values.

### The late change request (the real test)

Weather-O-Rama's CEO calls: they can't ship without a **Heat Index display**. Heat index combines temperature and humidity into an apparent "feels like" temperature. The book points you to a downloadable formula file for it. You may also use a simplified stand-in formula, since the formula isn't the lesson.

Add `HeatIndexDisplay` **without touching `WeatherData` or any existing display.** If you had to edit any of them, the pattern didn't take.

### Expected behavior (your wording will differ)

```
Current conditions: 80.0F degrees and 65.0% humidity
Avg/Max/Min temperature = 80.0/80.0/80.0
Forecast: Improving weather on the way!
Heat index is 82.95
```

---

## Stretch goals

- **Push vs pull.** Rewrite so `WeatherData` only announces "something changed" and the displays call getters. What gets easier? What gets uglier?
- **Typed event.** Replace the three loose parameters with a small immutable `Measurement` record. What happens to your observer interface when a fourth measurement (wind speed) arrives?
- **Safe notification.** What happens if one display throws an exception? Make sure the others still update.
- **Self-unregistering display.** Let a display remove itself during a notification. What breaks, and what collection type fixes it?

---

## Reflection (fill in after you finish)

**Which smell triggered the pattern?**
-

**Subject and observer are loosely coupled. What exactly does each know about the other?**
-

**When would I not use Observer here? (Hint: one display, forever, and order matters.)**
-

**My twist:** apply it to something that isn't weather (order status, price alerts, config reloads).
-
