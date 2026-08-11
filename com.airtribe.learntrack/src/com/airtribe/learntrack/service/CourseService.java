package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseService {

    private final List<Course> courses = new ArrayList<>();

    public Course addCourse(String courseName, String description, Integer durationInWeeks)
            throws InvalidInputException {

        courseName = InputValidator.requireNonEmptyString(courseName, "Course name");
        description = InputValidator.requireNonEmptyString(description, "Description");
        InputValidator.requireNonNull(durationInWeeks, "Duration in weeks");
        if (durationInWeeks <= 0) {
            throw new InvalidInputException("Duration in weeks must be a positive integer.");
        }

        Course course = new Course(courseName, description, durationInWeeks);
        course.setId(IdGenerator.getNextCourseId());
        courses.add(course);
        return course;
    }

    public List<Course> listCourses() {
        return Collections.unmodifiableList(new ArrayList<>(courses));
    }

    public Course findCourseById(Integer courseId) throws EntityNotFoundException, InvalidInputException {
        InputValidator.requireNonNull(courseId, "Course ID");
        for (Course course : courses) {
            if (courseId.equals(course.getId())) {
                return course;
            }
        }
        throw new EntityNotFoundException("Course with ID " + courseId + " not found.");
    }

    public Course activateCourse(Integer courseId) throws EntityNotFoundException, InvalidInputException {
        Course course = findCourseById(courseId);
        if (course.isActive()) {
            throw new InvalidInputException("Course with ID " + courseId + " is already active.");
        }
        course.setActive(true);
        return course;
    }

    public Course deactivateCourse(Integer courseId) throws EntityNotFoundException, InvalidInputException {
        Course course = findCourseById(courseId);
        if (!course.isActive()) {
            throw new InvalidInputException("Course with ID " + courseId + " is already inactive.");
        }
        course.setActive(false);
        return course;
    }
}
