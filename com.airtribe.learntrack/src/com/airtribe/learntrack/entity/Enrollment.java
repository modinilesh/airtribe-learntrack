package com.airtribe.learntrack.entity;

import java.time.LocalDate;

public class Enrollment {

    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private LocalDate enrollmentDate;
    private Status status;

    public Enrollment() {
        this.status = Status.ACTIVE;
    }

    public Enrollment(Integer studentId, Integer courseId, LocalDate enrollmentDate) {
        if (studentId == null) {
            throw new IllegalArgumentException("Student ID cannot be null.");
        }
        if (courseId == null) {
            throw new IllegalArgumentException("Course ID cannot be null.");
        }
        if (enrollmentDate == null) {
            throw new IllegalArgumentException("Enrollment date cannot be null.");
        }
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = Status.ACTIVE;
    }

    public Integer getId() {
        return id;
    }

    /**
     * ID may be assigned once (typically by the service via IdGenerator).
     * Subsequent changes are rejected to avoid broken lookups.
     */
    public void setId(Integer id) {
        if (this.id != null) {
            throw new IllegalStateException("ID is already assigned and cannot be changed.");
        }
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null.");
        }
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null.");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "Enrollment{id=" + id
                + ", studentId=" + studentId
                + ", courseId=" + courseId
                + ", enrollmentDate=" + enrollmentDate
                + ", status=" + status + '}';
    }
}
