package ui;

import entity.Course;
import entity.Enrollment;
import entity.Status;
import entity.Student;
import exception.EntityNotFoundException;
import exception.InvalidInputException;
import service.CourseService;
import service.EnrollmentService;
import service.StudentService;
import util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService =
            new EnrollmentService(studentService, courseService);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            try {
                displayMainMenu();
                int choice = InputValidator.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        handleStudentMenu();
                        break;
                    case 2:
                        handleCourseMenu();
                        break;
                    case 3:
                        handleEnrollmentMenu();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Exiting LearnTrack. Goodbye!");
                        break;
                    default:
                        System.out.println("Option not found. Please choose a valid menu option.");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println();
        System.out.println("========== LearnTrack ==========");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void handleStudentMenu() {
        boolean back = false;
        while (!back) {
            try {
                displayStudentMenu();
                int choice = InputValidator.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        searchStudentById();
                        break;
                    case 4:
                        deactivateStudent();
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        System.out.println("Option not found. Please choose a valid menu option.");
                }
            } catch (InvalidInputException | EntityNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void displayStudentMenu() {
        System.out.println();
        System.out.println("--- Student Management ---");
        System.out.println("1. Add new student");
        System.out.println("2. View all students");
        System.out.println("3. Search student by ID");
        System.out.println("4. Deactivate a student");
        System.out.println("0. Back to main menu");
        System.out.print("Enter your choice: ");
    }

    private static void addStudent() throws InvalidInputException {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter batch: ");
        String batch = scanner.nextLine();

        Student student = studentService.addStudent(firstName, lastName, email, batch);
        System.out.println("Student added successfully: " + student);
    }

    private static void viewAllStudents() {
        List<Student> students = studentService.listStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("--- All Students ---");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void searchStudentById() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter student ID: ");
        int studentId = InputValidator.parseInt(scanner.nextLine());
        Student student = studentService.findStudentById(studentId);
        System.out.println(student);
    }

    private static void deactivateStudent() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter student ID to deactivate: ");
        int studentId = InputValidator.parseInt(scanner.nextLine());
        Student student = studentService.deactivateStudent(studentId);
        System.out.println("Student deactivated successfully: " + student);
    }

    private static void handleCourseMenu() {
        boolean back = false;
        while (!back) {
            try {
                displayCourseMenu();
                int choice = InputValidator.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addCourse();
                        break;
                    case 2:
                        viewAllCourses();
                        break;
                    case 3:
                        activateCourse();
                        break;
                    case 4:
                        deactivateCourse();
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        System.out.println("Option not found. Please choose a valid menu option.");
                }
            } catch (InvalidInputException | EntityNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void displayCourseMenu() {
        System.out.println();
        System.out.println("--- Course Management ---");
        System.out.println("1. Add new course");
        System.out.println("2. View all courses");
        System.out.println("3. Activate a course");
        System.out.println("4. Deactivate a course");
        System.out.println("0. Back to main menu");
        System.out.print("Enter your choice: ");
    }

    private static void addCourse() throws InvalidInputException {
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter duration in weeks: ");
        String durationInWeeks = scanner.nextLine();

        Course course = courseService.addCourse(courseName, description, durationInWeeks);
        System.out.println("Course added successfully: " + course);
    }

    private static void viewAllCourses() {
        List<Course> courses = courseService.listCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        System.out.println("--- All Courses ---");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static void activateCourse() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter course ID to activate: ");
        int courseId = InputValidator.parseInt(scanner.nextLine());
        Course course = courseService.activateCourse(courseId);
        System.out.println("Course activated successfully: " + course);
    }

    private static void deactivateCourse() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter course ID to deactivate: ");
        int courseId = InputValidator.parseInt(scanner.nextLine());
        Course course = courseService.deactivateCourse(courseId);
        System.out.println("Course deactivated successfully: " + course);
    }

    private static void handleEnrollmentMenu() {
        boolean back = false;
        while (!back) {
            try {
                displayEnrollmentMenu();
                int choice = InputValidator.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        enrollStudent();
                        break;
                    case 2:
                        viewEnrollmentsForStudent();
                        break;
                    case 3:
                        markEnrollmentCompleted();
                        break;
                    case 4:
                        markEnrollmentCancelled();
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        System.out.println("Option not found. Please choose a valid menu option.");
                }
            } catch (InvalidInputException | EntityNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void displayEnrollmentMenu() {
        System.out.println();
        System.out.println("--- Enrollment Management ---");
        System.out.println("1. Enroll a student in a course");
        System.out.println("2. View enrollments for a student");
        System.out.println("3. Mark enrollment as completed");
        System.out.println("4. Mark enrollment as cancelled");
        System.out.println("0. Back to main menu");
        System.out.print("Enter your choice: ");
    }

    private static void enrollStudent() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter student ID: ");
        int studentId = InputValidator.parseInt(scanner.nextLine());
        System.out.print("Enter course ID: ");
        int courseId = InputValidator.parseInt(scanner.nextLine());

        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
        System.out.println("Enrollment created successfully: " + enrollment);
    }

    private static void viewEnrollmentsForStudent() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter student ID: ");
        int studentId = InputValidator.parseInt(scanner.nextLine());
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found for student ID " + studentId + ".");
            return;
        }
        System.out.println("--- Enrollments for Student " + studentId + " ---");
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }

    private static void markEnrollmentCompleted() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter enrollment ID: ");
        int enrollmentId = InputValidator.parseInt(scanner.nextLine());
        Enrollment enrollment = enrollmentService.updateEnrollmentStatus(enrollmentId, Status.COMPLETED);
        System.out.println("Enrollment marked as completed: " + enrollment);
    }

    private static void markEnrollmentCancelled() throws InvalidInputException, EntityNotFoundException {
        System.out.print("Enter enrollment ID: ");
        int enrollmentId = InputValidator.parseInt(scanner.nextLine());
        Enrollment enrollment = enrollmentService.updateEnrollmentStatus(enrollmentId, Status.CANCELLED);
        System.out.println("Enrollment marked as cancelled: " + enrollment);
    }
}
