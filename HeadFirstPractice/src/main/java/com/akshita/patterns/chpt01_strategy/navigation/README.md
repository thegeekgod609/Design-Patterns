# Strategy Pattern: The Navigator Problem

**Source:** [Refactoring.Guru: Strategy](https://refactoring.guru/design-patterns/strategy) (the navigation app scenario)
**Pattern:** Strategy (Behavioral)

---

## The problem

You build a navigation app for casual travelers, centered on a lovely map that helps people find their way around any city. The most requested feature is **automatic route planning**: type an address, see the fastest route drawn on the map.

Version 1 only builds routes over **roads**. Drivers rejoice. Then the reviews arrive: not everyone wants to drive on vacation. So you add **walking routes**. Then **public transport**. Then the roadmap whispers about **cycling routes**, and later a **sightseeing route** that passes through a city's tourist attractions.

Business is booming. The code is not.

Every new routing algorithm roughly doubles the size of the main navigator class, and it has become a monster nobody wants to open:

- **Fragile:** fixing a bug in one algorithm, or tweaking how a street is scored, touches the same giant class and can break routes that were working fine.
- **Slow teamwork:** the engineers you hired after launch keep colliding in the same file, and they spend more time resolving merge conflicts than building features.
- **Hard to extend:** every new transport mode means editing the core class again.

### What the bloated version looks like

```
buildRoute(mode, origin, destination):
    if mode == ROAD:        ...200 lines of road logic...
    else if mode == WALK:   ...150 lines of walking logic...
    else if mode == TRANSIT:...300 lines of timetable logic...
    else if mode == CYCLE:  ...
    else if mode == SIGHTSEEING: ...
    // ...and the map-drawing code lives in the same class
```

The class has two jobs: *drawing the route on the map* and *figuring out the route*. The map code doesn't care how a route was calculated. It only needs a list of checkpoints.

---

## The goal

Restructure the navigator so that:

1. Each routing algorithm is its own class, behind **one common interface**.
2. The navigator (the **context**) holds one routing strategy and **delegates** to it. It never decides which algorithm to use.
3. The user (through a UI button, say) can **switch the strategy at runtime**.
4. Adding a new routing mode means **adding a class**, and nothing in the navigator or the other strategies changes.

---

## What to build

Package: `com.akshita.patterns.ch01_strategy.navigator`

| Piece | Role |
|-------|------|
| `RouteStrategy` | Strategy interface with one method that takes an origin and a destination and returns the route's checkpoints |
| `RoadStrategy`, `WalkingStrategy`, `PublicTransportStrategy` | Concrete strategies for the first three modes |
| `CyclingStrategy`, `SightseeingStrategy` | The "later" additions. Add these **last**, as the open/closed test |
| `Navigator` | Context. Holds a strategy, has a setter to swap it, and "renders" the checkpoints it gets back |
| `Demo` | Plays the part of the UI buttons: picks a strategy, asks for a route, then switches and asks again |

Keep each algorithm simple. A fake implementation that returns a few made-up checkpoint names is plenty. **The structure is the exercise, not the routing math.**

### Checklist

- [ ] First, write the ugly single-class version with the `if/else` ladder, and commit it.
- [ ] Refactor it into Strategy, and commit again. The diff between the two is your lesson.
- [ ] `Navigator` contains **no** knowledge of road, walking, or transit logic.
- [ ] `Demo` switches strategy at runtime and the output visibly changes.
- [ ] Add `CyclingStrategy` and `SightseeingStrategy` after everything works, and check that **no existing class was edited**.
- [ ] Tests: give `Navigator` a fake strategy (a lambda works) and assert that it renders whatever checkpoints it was handed.

### Expected behavior (your wording will differ)

```
Mode: ROAD           -> Home -> Highway -> Destination
Mode: WALKING        -> Home -> Park -> Destination
Mode: PUBLIC TRANSIT -> Home -> Bus stop -> Metro -> Destination
```

---

## Strategy vs. its look-alikes

While you build this, ask yourself why it is *not*:

- **State:** the navigator's mode is chosen by the client, not by the navigator moving itself between modes.
- **Template Method:** you swap whole algorithms through composition, not by subclassing the navigator.

---

## Reflection (fill in after you finish)

**Which smell triggered the pattern?**
-

**Where could this go wrong, i.e. when would I not use Strategy?**
-

**Could a lambda replace some of these classes? When is a class still better?**
-

**One real system I know where algorithms are swapped like this:**
-
