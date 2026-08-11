package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Status;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {

    private final List<Enrollment> enrollments = new ArrayList<>();
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(Integer studentId, Integer courseId)
            throws EntityNotFoundException, InvalidInputException {

        validateStudentForCourseEnrollment(studentId, courseId);
        Enrollment enrollment = new Enrollment(studentId, courseId, LocalDate.now());
        enrollment.setId(IdGenerator.getNextEnrollmentId());
        enrollments.add(enrollment);
        return enrollment;
    }

    public List<Enrollment> getEnrollmentsByStudentId(Integer studentId)
            throws EntityNotFoundException, InvalidInputException {
        studentService.findStudentById(studentId);
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (studentId.equals(enrollment.getStudentId())) {
                result.add(enrollment);
            }
        }
        return result;
    }

    public Enrollment updateEnrollmentStatus(Integer enrollmentId, Status status)
            throws EntityNotFoundException, InvalidInputException {
        InputValidator.requireNonNull(enrollmentId, "Enrollment ID");
        InputValidator.requireNonNull(status, "Status");

        Enrollment enrollment = findEnrollmentById(enrollmentId);
        validateStatusTransition(enrollment.getStatus(), status);
        enrollment.setStatus(status);
        return enrollment;
    }

    public Enrollment findEnrollmentById(Integer enrollmentId)
            throws EntityNotFoundException, InvalidInputException {
        InputValidator.requireNonNull(enrollmentId, "Enrollment ID");
        for (Enrollment enrollment : enrollments) {
            if (enrollmentId.equals(enrollment.getId())) {
                return enrollment;
            }
        }
        throw new EntityNotFoundException("Enrollment with ID " + enrollmentId + " not found.");
    }

    public void validateStudentForCourseEnrollment(Integer studentId, Integer courseId)
            throws EntityNotFoundException, InvalidInputException {
        InputValidator.requireNonNull(studentId, "Student ID");
        InputValidator.requireNonNull(courseId, "Course ID");

        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);

        if (!student.isActive()) {
            throw new InvalidInputException("Cannot enroll inactive student with ID " + studentId + ".");
        }
        if (!course.isActive()) {
            throw new InvalidInputException("Cannot enroll in inactive course with ID " + courseId + ".");
        }

        for (Enrollment enrollment : enrollments) {
            if (studentId.equals(enrollment.getStudentId())
                    && courseId.equals(enrollment.getCourseId())
                    && enrollment.getStatus() == Status.ACTIVE) {
                throw new InvalidInputException(
                        "Student " + studentId + " is already actively enrolled in course " + courseId + ".");
            }
        }
    }

    private void validateStatusTransition(Status current, Status next) throws InvalidInputException {
        if (current == next) {
            throw new InvalidInputException("Enrollment is already in status " + next + ".");
        }
        if (current == Status.COMPLETED || current == Status.CANCELLED) {
            throw new InvalidInputException(
                    "Cannot change status of a " + current + " enrollment. Only ACTIVE enrollments can be updated.");
        }
        if (next != Status.COMPLETED && next != Status.CANCELLED) {
            throw new InvalidInputException("Invalid status transition from " + current + " to " + next + ".");
        }
    }
}
