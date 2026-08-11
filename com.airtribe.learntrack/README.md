# LearnTrack

LearnTrack is a simple console app for managing students, courses, and enrollments.

You can:

- Add students, list them, search by ID, update a student, and deactivate a student (no hard delete)
- Add courses, list them, and turn a course on or off
- Enroll a student in a course, see a student's enrollments, and mark an enrollment as completed or cancelled

The UI lives in `Main`. Business logic sits in service classes (`StudentService`, `CourseService`, `EnrollmentService`) so the menu code stays thin.

## Project layout

```
src/
  com/airtribe/learntrack/
    entity/       Student, Course, Enrollment, Person, Trainer, Status
    service/      Student, Course, and Enrollment services
    exception/    EntityNotFoundException, InvalidInputException
    util/         IdGenerator, InputValidator
    ui/           Main (menu-driven console)
  docs/           Extra notes (setup, JVM basics)
```

## How to compile and run

You need a JDK installed (`java -version` should work).

From the project root (`com.airtribe.learntrack/`):

```bash
mkdir -p out
javac -d out $(find src/com -name "*.java")
java -cp out com.airtribe.learntrack.ui.Main
```

Using `-d out` keeps `.class` files out of your source folders.

### IntelliJ

Open the project, open `src/com/airtribe/learntrack/ui/Main.java`, and hit Run. IntelliJ compiles into its own output folder, so you should only see `.java` files under `src`.

## Clean code notes (short)

- Method names say what they do: `addStudent`, `findCourseById`, `deactivateStudent`
- Menu handlers are split by area (student / course / enrollment) instead of one giant method
- Services own the data and rules; `Main` mostly prints menus and reads input
- IDs are generated once via `IdGenerator` in the service layer and cannot be reassigned
