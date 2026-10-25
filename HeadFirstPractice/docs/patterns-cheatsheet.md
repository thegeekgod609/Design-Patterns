# Design Patterns Cheatsheet

A one-page memory jogger for all 23 GoF patterns. Add your own notes as you go: a cheatsheet you didn't write is just someone else's cheatsheet.

---

## 1. The principles underneath everything

1. Encapsulate what varies.
2. Favor composition over inheritance.
3. Program to an interface, not an implementation.
4. Strive for loosely coupled designs.
5. Open for extension, closed for modification (OCP).
6. Depend on abstractions, not concrete classes (DIP).
7. Principle of Least Knowledge: talk only to your friends.
8. Hollywood Principle: don't call us, we'll call you.
9. One class, one reason to change (SRP).

---

## 2. Smell → pattern

Real code never says "I need a Decorator." It says something is ugly. Learn to read the smell.

| The smell | Try |
|-----------|-----|
| Big `if/else` or `switch` choosing an algorithm | **Strategy** |
| Big `if/else` on an object's *state* that changes its behavior | **State** |
| One object changes and many others must react | **Observer** |
| Adding features by subclassing causes a class explosion | **Decorator** |
| `new ConcreteClass()` scattered everywhere, hard to swap | **Factory Method / Abstract Factory** |
| Constructor with 9 parameters, half optional | **Builder** |
| Need exactly one shared instance (think twice first) | **Singleton** |
| Need undo, queueing, logging, or scheduling of requests | **Command** |
| Library's interface doesn't match what your code expects | **Adapter** |
| Client must call five subsystem classes in the right order | **Facade** |
| Same algorithm skeleton, only a few steps differ | **Template Method** |
| Want to traverse a collection without exposing its internals | **Iterator** |
| Tree structure where leaves and branches should be treated alike | **Composite** |
| Control access, lazy-load, or add cross-cutting logic around an object | **Proxy** |
| Request should pass along a pipeline until someone handles it | **Chain of Responsibility** |
| Many objects talk to many others in a tangle | **Mediator** |
| Need to snapshot and restore object state | **Memento** |
| Thousands of near-identical objects eating memory | **Flyweight** |
| Abstraction and implementation both vary independently | **Bridge** |
| Need to add operations to a class hierarchy without touching it | **Visitor** |
| Need to evaluate rules or expressions in a small language | **Interpreter** |
| Creating an object is expensive; copying one is cheap | **Prototype** |

---

## 3. All 23 at a glance

### Creational

| Pattern | One-liner | Java / Spring sighting |
|---------|-----------|------------------------|
| **Singleton** | One instance, global access point | `Runtime.getRuntime()`, Spring beans (container-managed), enum singleton |
| **Factory Method** | Subclass decides which concrete class to instantiate | `Calendar.getInstance()`, `List.of()`, `NumberFormat.getInstance()` |
| **Abstract Factory** | Create families of related objects without naming concrete classes | `DocumentBuilderFactory`, JDBC-style driver families |
| **Builder** | Construct complex objects step by step | `StringBuilder`, `HttpRequest.newBuilder()`, Lombok `@Builder` |
| **Prototype** | Create new objects by cloning an existing one | `Object.clone()` (famously awkward), copy constructors |

### Structural

| Pattern | One-liner | Java / Spring sighting |
|---------|-----------|------------------------|
| **Adapter** | Convert one interface into another the client expects | `Arrays.asList()`, `InputStreamReader` |
| **Bridge** | Decouple abstraction from implementation so both can vary | JDBC (`Driver` vs `DriverManager`), SLF4J |
| **Composite** | Treat individual objects and groups uniformly | `java.awt.Container`, Swing component trees |
| **Decorator** | Wrap an object to add behavior dynamically | `java.io` streams, `Collections.unmodifiableList()` |
| **Facade** | One simple interface to a complex subsystem | SLF4J, `java.net.http.HttpClient`, service-layer classes |
| **Flyweight** | Share common state to save memory | `Integer.valueOf()` cache, `String` pool |
| **Proxy** | Stand-in that controls access to another object | `java.lang.reflect.Proxy`, Spring AOP, `@Transactional`, Hibernate lazy loading |

### Behavioral

| Pattern | One-liner | Java / Spring sighting |
|---------|-----------|------------------------|
| **Chain of Responsibility** | Pass a request along handlers until one handles it | Servlet `Filter` chain, Spring Security filter chain |
| **Command** | Wrap a request as an object | `Runnable`, `Callable`, `ExecutorService` tasks |
| **Interpreter** | Define a grammar and interpret sentences in it | `java.util.regex.Pattern`, Spring Expression Language (SpEL) |
| **Iterator** | Sequential access without exposing the structure | `Iterator`, `Iterable`, for-each loop |
| **Mediator** | Central hub so objects don't talk to each other directly | Chat room / event bus designs, UI form controllers |
| **Memento** | Capture and restore state without breaking encapsulation | Undo stacks, serialization snapshots |
| **Observer** | Notify dependents automatically when state changes | `PropertyChangeListener`, Spring `ApplicationEvent`, `Flow` API |
| **State** | Behavior changes when internal state changes | Workflow engines, Spring Statemachine |
| **Strategy** | Interchangeable algorithms behind one interface | `Comparator`, `Collections.sort()` |
| **Template Method** | Fix the algorithm skeleton, let subclasses fill in steps | `HttpServlet.doGet()`, `AbstractList`, `JdbcTemplate` |
| **Visitor** | Add operations to a structure without modifying its classes | `Files.walkFileTree` with `FileVisitor` |

---

## 4. When NOT to use it

| Pattern | Skip it when... |
|---------|-----------------|
| Strategy | There are two algorithms and they'll never change. An `if` is fine. |
| Observer | The update chain gets so long you can't predict what fires when. |
| Decorator | You end up with a stack of ten wrappers nobody can debug. |
| Factory | You're only ever creating one concrete type. |
| Singleton | You mean "global variable." It hurts testing, so let a DI container manage the scope. |
| Command | A plain method call does the job and you need no undo, queue, or log. |
| Adapter | You own both sides and can just change one of them. |
| Facade | It turns into a god class that does everything. |
| Template Method | Inheritance is awkward here. A Strategy gives more flexibility. |
| Composite | Leaves and branches really behave differently. |
| State | There are two states and one boolean flag does the job. |
| Proxy | The indirection adds confusion and no real control. |
| Visitor | The class hierarchy changes often. Every new class forces changes to every visitor. |
| Flyweight | Memory isn't actually a problem. Measure first. |
| Prototype | Constructors are cheap. |

---

## 5. Easily confused pairs

| Pair | The difference |
|------|----------------|
| **Strategy vs State** | Same shape, different intent. With Strategy the *client* picks the algorithm. With State the *object itself* transitions between behaviors. |
| **Decorator vs Proxy** | Decorator *adds* behavior. Proxy *controls access* to the same behavior. |
| **Decorator vs Adapter vs Facade** | Decorator keeps the interface and adds responsibility. Adapter *changes* the interface. Facade *simplifies* a whole subsystem. |
| **Factory Method vs Abstract Factory** | Factory Method is one method, overridden by subclasses. Abstract Factory is an object that creates a *family* of related objects. |
| **Template Method vs Strategy** | Template Method uses inheritance (the skeleton is fixed). Strategy uses composition (the whole algorithm is swappable). |
| **Observer vs Mediator** | Observer is one-to-many broadcasting. Mediator is many-to-many coordination through a hub. |
| **Command vs Strategy** | Command wraps a *request* (do this). Strategy wraps an *algorithm* (how to do it). |

---

## 6. My notes

Use this space for "aha" moments, gotchas, and real places you've spotted each pattern at work.

### Strategy
-

### Observer
-

### Decorator
-

### Factory
-

### Singleton
-

### Command
-

### Adapter / Facade
-

### Template Method
-

### Iterator / Composite
-

### State
-

### Proxy
-

### Compound (MVC)
-

### Leftovers
-
