package service;

import entity.Course;
import exception.EntityNotFoundException;
import exception.InvalidInputException;
import util.IdGenerator;
import util.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private final List<Course> courses = new ArrayList<>();

    public Course addCourse(String courseName, String description, String durationInWeeks)
            throws InvalidInputException {

        //Appropriate validation check can be made
       courseName = InputValidator.requireNonEmptyString(courseName, "Course name");
       description = InputValidator.requireNonEmptyString(description, "Description");
       durationInWeeks = InputValidator.requireNonEmptyString(durationInWeeks, "Duration");

        Course course = new Course(courseName, description, durationInWeeks);
        course.setId(IdGenerator.getNextCourseId());

        //Adding to DB
        courses.add(course);

        return course;
    }

    public List<Course> listCourses() {
        return new ArrayList<>(courses);
    }

    public Course findCourseById(Integer courseId) throws EntityNotFoundException {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                return course;
            }
        }
        throw new EntityNotFoundException("Course with ID " + courseId + " not found.");
    }

    public Course activateCourse(Integer courseId) throws EntityNotFoundException {
        Course course = findCourseById(courseId);
        course.setActive(true);
        return course;
    }

    public Course deactivateCourse(Integer courseId) throws EntityNotFoundException {
        Course course = findCourseById(courseId);
        course.setActive(false);
        return course;
    }
}
