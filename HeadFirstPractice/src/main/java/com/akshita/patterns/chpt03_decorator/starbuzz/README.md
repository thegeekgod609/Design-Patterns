# Decorator Pattern: The Starbuzz Coffee Problem

**Source:** *Head First Design Patterns*, Chapter 3 (Decorating Objects: the Starbuzz story)
**Pattern:** Decorator (Structural)

---

## The problem

Starbuzz Coffee is the fastest-growing coffee chain around. Look across the street from one Starbuzz and you'll find another. They grew so fast that their ordering system is struggling to keep up with the menu.

The original design looked sensible. An abstract `Beverage` class holds a description and an abstract `cost()` method. Each drink on the menu (house blend, dark roast, decaf, espresso) is a subclass that reports its own price. Done.

Then customers started asking for **condiments**: steamed milk, soy, mocha (chocolate for the uninitiated), and whipped milk on top. Starbuzz charges a little for each one, so the order system has to add them to the bill.

### Attempt 1: a subclass for every combination

Make `HouseBlendWithSteamedMilkAndMocha`, `DarkRoastWithSoyAndWhip`, `DecafWithMochaAndWhipAndSoy`... Four drinks and four condiments, in any combination, produce a staggering number of classes. The class diagram looks like a plate of spaghetti that has seen things.

Now ask: what happens when the price of milk goes up? What happens when a **caramel** topping arrives? You'd be editing or creating dozens of classes, all in lockstep.

### Attempt 2: stuff the condiments into the superclass

A smarter-sounding idea: give `Beverage` a boolean for each condiment, with getters and setters, and let `cost()` in the superclass add up the extras. Subclasses still add their own base price.

This looks fine until you think about the future:

| What changes | Why it hurts |
|--------------|--------------|
| A condiment's price changes | You edit existing, working code. |
| A new condiment appears | You add a new field and new methods to `Beverage`, and alter `cost()`. |
| A new drink arrives, say **iced tea** | It inherits `hasWhip()` and friends, even if whipped cream on iced tea is a crime. |
| A customer wants a **double mocha** | A boolean can't count to two. |

Both attempts violate two principles from the early chapters. Can you name which two before you read on? (Hint: one is about inheritance, one is about being open and closed.)

---

## The goal

Redesign so that:

1. Condiments are **not** baked into the drink classes, in subclass names or in boolean flags.
2. You can start with any drink, then **wrap it** with any number of condiments, in any order, including the same one twice.
3. The final object still **behaves like a drink**: it has a description and a total cost, so the order system doesn't care what's inside.
4. Adding a brand-new condiment means **adding a class**. No existing class is edited.

Principles to chase:

- **Classes should be open for extension but closed for modification.**
- Favor composition over inheritance.

---

## What to build

Package: `com.akshita.patterns.ch03_decorator.starbuzz`

| Piece | Role |
|-------|------|
| `Beverage` | The common type: a description and a `cost()` |
| `HouseBlend`, `DarkRoast`, `Decaf`, `Espresso` | Concrete drinks, each with its own base description and price |
| `CondimentDecorator` | Abstract wrapper that **is a** `Beverage` and **has a** `Beverage` |
| `Milk` (steamed milk), `Mocha`, `Soy`, `Whip` | Concrete decorators. Each adds its own text and price on top of whatever it wraps. |
| `Demo` | Orders a few drinks, including one with a double mocha |

Choose your own prices. The structure is the exercise, not the menu board.

Think about this as you design `CondimentDecorator`: the book makes it an abstract class that **redeclares the description method**. Why force every decorator to reimplement it? (What would the final description say if they didn't?) And why did the book use an abstract class for `Beverage` instead of an interface? Could an interface work here?

### Checklist

- [ ] First, write the ugly version, either the class-per-combination approach or the boolean-flags-in-superclass approach, and commit it.
- [ ] Refactor to Decorator and commit again. The diff is the lesson.
- [ ] A plain espresso prints its description and cost with no wrappers involved.
- [ ] A dark roast with **double mocha** and whip prints the correct combined description and total.
- [ ] Add a `Caramel` decorator **without editing any existing class**.
- [ ] Tests: the total equals base + sum of condiments, the description lists everything in wrap order, and a decorator-less drink is unaffected by the existence of decorators.

### Expected behavior (your wording and prices will differ)

```
Espresso $<price>
Dark Roast Coffee, Mocha, Mocha, Whip $<price>
House Blend Coffee, Soy, Mocha, Whip $<price>
```

---

## Stretch: sizes (this is where it gets interesting)

Starbuzz now offers three sizes: **tall, grande and venti**. The base `Beverage` gains a size, and condiment prices now **depend on the size** of the drink, so soy costs a different amount on a tall than on a venti.

- Where does the size live? How does a decorator learn the size of the drink it wraps?
- Which classes have to change? Did any "closed for modification" promise get broken? Is that acceptable, and why?
- What does this reveal about decorators that need to ask the wrapped object *specific* questions?

If your design gets awkward here, good. That awkwardness is a real, documented limit of the pattern, and you'll write it up in the reflection below.

---

## Reflection (fill in after you finish)

**Which two principles did the first two attempts violate?**
-

**Why is double mocha trivial with Decorator and impossible with booleans?**
-

**When would I *not* use Decorator here? (Hint: code that checks `instanceof` on the concrete drink.)**
-

**What was awkward about the size requirement?**
-

**My twist:** model something that isn't coffee (pizza toppings, car options, a text-processing pipeline).
-
