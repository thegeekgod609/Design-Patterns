# Strategy Pattern: The SimUDuck Problem

**Source:** *Head First Design Patterns*, Chapter 1 (Welcome to Design Patterns)
**Pattern:** Strategy (Behavioral)

---

## The problem

You've joined a company that makes **SimUDuck**, a duck pond simulation game. Ducks swim, quack, and look gorgeous on screen. The original designers did the textbook OO thing: one `Duck` superclass holding `quack()` and `swim()`, plus an abstract `display()`, with every species (`MallardDuck`, `RedheadDuck`, ...) inheriting from it and supplying its own looks.

Then the executives return from a golf off-site with a Big Idea: **ducks must fly**, in time for the shareholder meeting. The obvious fix is to add `fly()` to `Duck` and let every subclass inherit it. Five minutes of work.

At the demo, rubber ducks soar across the screen.

Rubber ducks don't fly. They squeak instead of quack. Wooden decoy ducks do neither. A change meant for *some* ducks leaked into *all* ducks, and the shareholders got a free comedy show.

### The fixes that don't work

| Attempt | Why it fails |
|---------|--------------|
| **Override in subclasses** (`RubberDuck.fly()` does nothing, `RubberDuck.quack()` squeaks) | Every new duck type means remembering which methods to override. Miss one and rubber ducks fly again. |
| **Put `fly()` and `quack()` into interfaces** (`Flyable`, `Quackable`) | Only ducks that need the behavior implement it, but you now **copy-paste the same flying code** into every flying subclass. Fix a bug in one and forget the other 40. |
| **Add more inheritance layers** (`FlyingDuck`, `QuietDuck`, `FlyingQuietDuck`...) | Class explosion. Behaviors don't fit neatly into a tree. |

The requirement that keeps breaking you: **behavior varies independently of species**, and you may need to change it **at runtime** (a model duck that gets a rocket engine mid-game).

---

## The goal

Redesign SimUDuck so that:

1. Fly and quack behaviors live **outside** the `Duck` class hierarchy.
2. Any duck can be given any fly behavior and any quack behavior.
3. Behaviors can be **swapped at runtime**.
4. Adding a new behavior (rocket-powered flight) requires **zero changes** to existing `Duck` classes.

Design principles to chase here:

- Identify the aspects that vary and separate them from what stays the same.
- Program to an interface, not an implementation.
- Favor composition over inheritance.

---

## What to build

Package: `com.akshita.patterns.ch01_strategy.duck`

| Piece | Role |
|-------|------|
| `FlyBehavior` | Strategy interface for flying |
| `FlyWithWings`, `FlyNoWay`, `FlyRocketPowered` | Concrete fly strategies |
| `QuackBehavior` | Strategy interface for quacking |
| `Quack`, `Squeak`, `MuteQuack` | Concrete quack strategies |
| `Duck` | Context. Holds a fly strategy and a quack strategy, delegates to them, and lets them be swapped |
| `MallardDuck`, `ModelDuck` (+ any others) | Concrete ducks that choose their starting behaviors |
| `Demo` | Runs a mallard, then gives a model duck rocket power at runtime |

### Checklist

- [ ] Type it from understanding. Peek at the book only when stuck.
- [ ] `Demo` prints something for each duck: how it looks, how it quacks, how it flies.
- [ ] `ModelDuck` starts out grounded, then **gets rocket power at runtime** and flies.
- [ ] Tests: swap in a fake behavior and assert that `Duck` calls it.
- [ ] No `if (duck instanceof ...)` anywhere. If you wrote one, the pattern didn't take.

### Expected behavior (your output will differ in wording)

```
Mallard: quacks, flies with wings
Model duck: can't fly
Model duck, after the rocket upgrade: flies like a rocket
```

---

## Reflection (fill in after you finish)

**Which smell triggered the pattern?**
-

**Where could this go wrong, i.e. when would I not use Strategy?**
-

**One place I've seen this in real code (JDK, Spring, work):**
-

**My twist:** apply it to something that isn't a duck.
-
