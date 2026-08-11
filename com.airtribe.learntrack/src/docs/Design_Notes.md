## Design Notes

### Why ArrayList instead of a plain array?

```
I used `ArrayList` because the number of students, courses, and enrollments is not fixed.
With an array, I have to define the size in advance and manually resize it if needed. `ArrayList` grows automatically and makes operations like `add()`, searching, and looping simpler.
```

### Where I used static members and why

```
**`IdGenerator`**
Counters and methods are static because IDs should be unique across the entire application, not per object.

**`InputValidator`**
Methods like `parseInt()` and `requireNonEmptyString()` are static because they don't maintain any state. They are just utility methods.

**`Main`**
The `main()` method is static because the JVM needs it to start the application. I also kept services and `Scanner` static so the menu methods can share them easily.

I kept business logic in services as instance methods because services maintain their own data.
```



### Where I used inheritance and what I gained

```
**`Student` and `Trainer` extend `Person`.**

`Person` contains common fields like `id`, name, and email. Instead of duplicating them, `Student` and `Trainer` inherit them.

`Student` adds its own fields like `batch` and `active`, and both subclasses can override `getDisplayName()`.

This gives me **code reuse, less duplication, and a cleaner model.**

**Custom Exceptions**

`EntityNotFoundException` and `InvalidInputException` extend `Exception`.

I use them to handle specific errors clearly, such as when an entity is not found or the input is invalid, instead of allowing the application to fail with a generic error.
```

