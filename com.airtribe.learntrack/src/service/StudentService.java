package service;

import entity.Student;
import exception.EntityNotFoundException;
import exception.InvalidInputException;
import util.IdGenerator;
import util.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final List<Student> students = new ArrayList<>();

    public Student addStudent(String firstName, String lastName, String email, String batch)
            throws InvalidInputException {
        firstName = InputValidator.requireNonEmptyString(firstName, "First name");
        lastName = InputValidator.requireNonEmptyString(lastName, "Last name");
        email = InputValidator.requireNonEmptyString(email, "Email");
        batch = InputValidator.requireNonEmptyString(batch, "Batch");

        Student student = new Student(firstName, lastName, email, batch);
        student.setId(IdGenerator.getNextStudentId());
        students.add(student);
        return student;
    }

    public List<Student> listStudents() {
        return new ArrayList<>(students);
    }

    public Student findStudentById(Integer studentId) throws EntityNotFoundException {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student with ID " + studentId + " not found.");
    }

    public Student deactivateStudent(Integer studentId) throws EntityNotFoundException {
        Student student = findStudentById(studentId);
        student.setActive(false);
        return student;
    }
}
