# Head First Design Patterns in Java

> Every pattern in the book, typed out by hand, tested, and explained in my own words.
> No copy-paste. No `AbstractSingletonProxyFactoryBean` (okay, maybe one).

Working through *Head First Design Patterns* chapter by chapter, with one rule: **if I can't type it from understanding, I don't know it yet.**

Each pattern gets:

- working Java code (the book's example, rebuilt from scratch),
- a `Demo` class so I can actually *see* it do its job,
- JUnit 5 tests proving the design is as flexible as the pattern promises,
- a short README: the problem, the pattern, the participants, and, most importantly, **when NOT to use it**,
- one twist of my own, applying the pattern to something other than ducks and coffee.

---

## Progress

| # | Pattern(s) | Category | Status |
|---|------------|----------|--------|
| 1 | [Strategy](src/main/java/com/akshita/patterns/ch01_strategy) | Behavioral | ⬜ |
| 2 | [Observer](src/main/java/com/akshita/patterns/ch02_observer) | Behavioral | ⬜ |
| 3 | [Decorator](src/main/java/com/akshita/patterns/ch03_decorator) | Structural | ⬜ |
| 4 | [Factory Method & Abstract Factory](src/main/java/com/akshita/patterns/ch04_factory) | Creational | ⬜ |
| 5 | [Singleton](src/main/java/com/akshita/patterns/ch05_singleton) | Creational | ⬜ |
| 6 | [Command](src/main/java/com/akshita/patterns/ch06_command) | Behavioral | ⬜ |
| 7 | [Adapter & Facade](src/main/java/com/akshita/patterns/ch07_adapter_facade) | Structural | ⬜ |
| 8 | [Template Method](src/main/java/com/akshita/patterns/ch08_template_method) | Behavioral | ⬜ |
| 9 | [Iterator & Composite](src/main/java/com/akshita/patterns/ch09_iterator_composite) | Behavioral / Structural | ⬜ |
| 10 | [State](src/main/java/com/akshita/patterns/ch10_state) | Behavioral | ⬜ |
| 11 | [Proxy](src/main/java/com/akshita/patterns/ch11_proxy) | Structural | ⬜ |
| 12 | [Compound Patterns (MVC)](src/main/java/com/akshita/patterns/ch12_compound_mvc) | Compound | ⬜ |
| 14 | [Leftovers: Bridge, Builder, Chain of Responsibility, Flyweight, Interpreter, Mediator, Memento, Prototype, Visitor](src/main/java/com/akshita/patterns/ch14_leftovers) | Mixed | ⬜ |

Chapter numbers follow the 2nd edition. If yours differs, rename the folders to match your table of contents; the code doesn't care.

Quick reference for all 23 patterns: [`docs/patterns-cheatsheet.md`](docs/patterns-cheatsheet.md)

---

## Project structure

```
head-first-design-patterns/
├── README.md
├── pom.xml
├── .gitignore
├── docs/
│   └── patterns-cheatsheet.md
└── src/
    ├── main/java/com/akshita/patterns/
    │   ├── Main.java
    │   ├── ch01_strategy/
    │   │   ├── README.md        # problem, pattern, participants, when NOT to use
    │   │   ├── Duck.java
    │   │   ├── FlyBehavior.java
    │   │   └── Demo.java
    │   ├── ch02_observer/
    │   └── ...
    └── test/java/com/akshita/patterns/
        └── ch01_strategy/
            └── DuckTest.java
```

Each pattern lives in its own package and is self-contained. Delete one and nothing else breaks, which is rather the point of the book.

---

## Running things

**Prerequisites:** JDK 17+ and Maven 3.9+ (or just IntelliJ's bundled Maven).

```bash
# Build and run every test
mvn clean test

# Compile, then run a specific demo
mvn compile
java -cp target/classes com.akshita.patterns.ch01_strategy.Demo
```

Or open the project in IntelliJ and press the green play arrow next to any `main`.

---

## My workflow for each pattern

1. **Read the chapter first, with no code.** Do the "Sharpen your pencil" exercises, suffering included.
2. **Close the book and type the code from understanding.** Peek only when truly stuck.
3. **Write a `Demo`** that prints something visible.
4. **Write tests.** Swap in a fake behavior and assert that it works. If swapping is painful, the pattern isn't doing its job.
5. **Write the pattern README** in my own words, including *when not to use it*.
6. **Add a twist:** apply the pattern to a problem of my own.
7. **Commit once per pattern:** `feat(ch03): decorator - coffee shop with condiments + tests`

---

## Design principles that keep showing up

- Encapsulate what varies
- Favor composition over inheritance
- Program to an interface, not an implementation
- Strive for loosely coupled designs between objects that interact
- Classes should be open for extension but closed for modification
- Depend on abstractions, not on concrete classes
- Talk only to your friends (Principle of Least Knowledge)
- Don't call us, we'll call you (Hollywood Principle)
- A class should have only one reason to change

The patterns are mostly these nine ideas wearing different outfits.

---

## Resources

- *Head First Design Patterns* (Freeman & Robson, et al.), the book this repo follows
- *Design Patterns: Elements of Reusable Object-Oriented Software* (Gamma, Helm, Johnson, Vlissides), the original GoF book
- [Refactoring.Guru: Design Patterns](https://refactoring.guru/design-patterns) for a good second opinion with diagrams

---

## A note on patternitis

Knowing a pattern is a skill. Knowing when to leave it alone is a better one. If the simple `if` statement works and won't change, the simple `if` statement wins.
