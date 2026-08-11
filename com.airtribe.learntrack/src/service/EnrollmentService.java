package service;

import entity.Course;
import entity.Enrollment;
import entity.Status;
import entity.Student;
import exception.EntityNotFoundException;
import exception.InvalidInputException;
import util.IdGenerator;

import java.util.ArrayList;
import java.util.Date;
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
        Enrollment enrollment = new Enrollment(studentId, courseId, new Date());
        enrollment.setId(IdGenerator.getNextEnrollmentId());
        enrollments.add(enrollment);
        return enrollment;
    }

    public List<Enrollment> getEnrollmentsByStudentId(Integer studentId) throws EntityNotFoundException {
        studentService.findStudentById(studentId);
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId().equals(studentId)) {
                result.add(enrollment);
            }
        }
        return result;
    }

    public Enrollment updateEnrollmentStatus(Integer enrollmentId, Status status)
            throws EntityNotFoundException {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollment.setStatus(status);
        return enrollment;
    }

    public Enrollment findEnrollmentById(Integer enrollmentId) throws EntityNotFoundException {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId().equals(enrollmentId)) {
                return enrollment;
            }
        }
        throw new EntityNotFoundException("Enrollment with ID " + enrollmentId + " not found.");
    }

    public void validateStudentForCourseEnrollment(int studentId, int courseId) throws EntityNotFoundException, InvalidInputException {
        //Fetch student and course by id
        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);

        //validation check
        if (!student.isActive()) {
            throw new InvalidInputException("Cannot enroll inactive student with ID " + studentId + ".");
        }
        if (!course.isActive()) {
            throw new InvalidInputException("Cannot enroll in inactive course with ID " + courseId + ".");
        }

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId().equals(studentId)
                    && enrollment.getCourseId().equals(courseId)
                    && enrollment.getStatus() == Status.ACTIVE) {
                throw new InvalidInputException(
                        "Student " + studentId + " is already actively enrolled in course " + courseId + ".");
            }
        }
    }
}
