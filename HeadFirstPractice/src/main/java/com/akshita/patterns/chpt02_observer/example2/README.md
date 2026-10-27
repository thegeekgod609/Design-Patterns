# Observer Pattern: The Editor and Its Event Listeners

**Source:** [Refactoring.Guru: Observer](https://refactoring.guru/design-patterns/observer) (the customer/store problem and the text editor example)
**Pattern:** Observer, also known as Event-Subscriber or Listener (Behavioral)

---

## The problem

Start with a shopping story. A customer wants a new phone that the store will stock soon. They have two bad options:

- **Check the store every day.** Most trips are wasted, because the phone isn't there yet.
- **Let the store email everyone whenever anything arrives.** That saves some trips, but it spams the customers who don't care about phones.

Either the customer wastes time or the store wastes effort notifying the wrong people. What both sides really need is a **subscription**: the customer says "tell me about *this*," and the store notifies only those people.

Now translate that to software. You're building a **text editor**. Things happen to files: they get **opened**, they get **saved**. Other parts of the system care:

- A **logger** wants to write a line to a log file when a file is opened.
- An **email alert service** wants to notify an admin when a file is saved.
- Next quarter, someone wants a backup service, and the quarter after that, a sync tool.

The naive editor calls each of these services by name from inside `openFile()` and `saveFile()`. Now the editor is coupled to logging, email, backups and sync. Every new reaction means editing the editor, and the editor can't be reused without dragging those services along.

There's a second wrinkle: the logger cares about **open** events and the email service cares about **save** events. A single "something happened" broadcast would send each of them events they don't want. That's the spam problem again.

---

## The goal

Redesign so that:

1. The editor (the **publisher**) knows nothing about concrete listeners. It only knows a small listener interface.
2. Listeners can **subscribe and unsubscribe at runtime**.
3. Listeners subscribe to **specific event types** (`"open"`, `"save"`), so they only hear what they asked for.
4. The editor **doesn't inherit** its subscription logic. A separate helper object manages subscribers, and the editor *has* one. (Inheritance often isn't available, because your publisher may already extend something else. Composition always works.)
5. Adding a new listener means writing a new class and registering it, with no edits to the editor.

---

## What to build

Package: `com.akshita.patterns.ch02_observer.eventlistener`

| Piece | Role |
|-------|------|
| `EventListener` | Subscriber interface with a single method that receives the file name |
| `EventManager` | Holds subscribers grouped by event type. Offers subscribe, unsubscribe and notify. |
| `Editor` | The publisher. Owns an `EventManager`. Opening and saving a file notify the matching event type. |
| `LoggingListener` | Writes a message about the file to a log. A `System.out.println` is fine for practice. |
| `EmailAlertsListener` | "Sends" an email about the file. Print it instead of really emailing. |
| `Demo` | The "application config": creates the editor, subscribes the logger to opens and the email alerts to saves, then opens and saves a file |

### Checklist

- [ ] First, write the ugly version where `Editor` calls the logger and email service directly, and commit it.
- [ ] Refactor to Observer and commit again.
- [ ] Opening a file triggers **only** the logger. Saving triggers **only** the email alerts. Prove it with output.
- [ ] Unsubscribe a listener at runtime and show that it goes quiet.
- [ ] `Editor` has no `import` of any concrete listener.
- [ ] Add a third listener (a backup service, say) **without editing** `Editor` or `EventManager`.
- [ ] Tests: register a fake listener (a lambda works), trigger the event, assert it received the right file name. Then assert a listener subscribed to `"open"` does **not** hear `"save"`.

### Expected behavior (your wording will differ)

```
Log: Someone has opened the file: notes.txt
Email to admin@example.com: Someone has changed the file: notes.txt
```

---

## Questions to answer as you build

- The listener interface has a single method. Could a **lambda** replace the whole listener class hierarchy? What would you lose?
- The source notes a known downside: subscribers are notified in **no guaranteed order**. What would break if listener B secretly depended on listener A having run first?
- What happens to `Editor.saveFile()` if one listener throws an exception? Should the rest still run?
- How is this different from the Weather Station version, where the publisher implements the subscription methods itself?

---

## Reflection (fill in after you finish)

**Which smell triggered the pattern?**
-

**When would I not use Observer here? (Hint: one listener, fixed forever, where order matters.)**
-

**Where have I seen "event type → listeners" in real code (GUI frameworks, Spring events, work)?**
-

**My twist:** apply it to something that isn't an editor.
-
