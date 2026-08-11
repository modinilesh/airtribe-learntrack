package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentService {

    private final List<Student> students = new ArrayList<>();

    public Student addStudent(String firstName, String lastName, String email, String batch)
            throws InvalidInputException {
        firstName = InputValidator.requireNonEmptyString(firstName, "First name");
        lastName = InputValidator.requireNonEmptyString(lastName, "Last name");
        email = InputValidator.requireValidEmail(email);
        batch = InputValidator.requireNonEmptyString(batch, "Batch");
        ensureEmailUnique(email, null);

        Student student = new Student(firstName, lastName, email, batch);
        student.setId(IdGenerator.getNextStudentId());
        students.add(student);
        return student;
    }

    public List<Student> listStudents() {
        return Collections.unmodifiableList(new ArrayList<>(students));
    }

    public Student findStudentById(Integer studentId) throws EntityNotFoundException, InvalidInputException {
        InputValidator.requireNonNull(studentId, "Student ID");
        for (Student student : students) {
            if (studentId.equals(student.getId())) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student with ID " + studentId + " not found.");
    }

    public Student updateStudent(Integer studentId, String firstName, String lastName, String email, String batch)
            throws EntityNotFoundException, InvalidInputException {
        Student student = findStudentById(studentId);
        firstName = InputValidator.requireNonEmptyString(firstName, "First name");
        lastName = InputValidator.requireNonEmptyString(lastName, "Last name");
        email = InputValidator.requireValidEmail(email);
        batch = InputValidator.requireNonEmptyString(batch, "Batch");
        ensureEmailUnique(email, studentId);

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBatch(batch);
        return student;
    }

    /**
     * Soft-remove: deactivates the student (no hard delete).
     */
    public Student removeStudent(Integer studentId) throws EntityNotFoundException, InvalidInputException {
        return deactivateStudent(studentId);
    }

    public Student deactivateStudent(Integer studentId) throws EntityNotFoundException, InvalidInputException {
        Student student = findStudentById(studentId);
        if (!student.isActive()) {
            throw new InvalidInputException("Student with ID " + studentId + " is already inactive.");
        }
        student.setActive(false);
        return student;
    }

    private void ensureEmailUnique(String email, Integer excludeStudentId) throws InvalidInputException {
        for (Student student : students) {
            if (excludeStudentId != null && excludeStudentId.equals(student.getId())) {
                continue;
            }
            if (email.equalsIgnoreCase(student.getEmail())) {
                throw new InvalidInputException("A student with email '" + email + "' already exists.");
            }
        }
    }
}
