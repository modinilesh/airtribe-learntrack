package com.airtribe.learntrack.entity;

public class Course {

    private Integer id;
    private String courseName;
    private String description;
    private Integer durationInWeeks;
    private boolean active;

    public Course() {
        this.active = true;
    }

    public Course(String courseName, String description, Integer durationInWeeks) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        if (durationInWeeks == null || durationInWeeks <= 0) {
            throw new IllegalArgumentException("Duration in weeks must be a positive integer.");
        }
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.active = true;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(Integer durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Course{id=" + id
                + ", courseName='" + courseName + '\''
                + ", description='" + description + '\''
                + ", durationInWeeks=" + durationInWeeks
                + ", active=" + active + '}';
    }
}
