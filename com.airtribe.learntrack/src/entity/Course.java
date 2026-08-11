package entity;

public class Course {

    private Integer id;
    private String courseName;
    private String description;
    private String durationInWeeks;
    private boolean active;

    public Course() {
    }

    public Course(String courseName, String description, String durationInWeeks) {
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.active = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(String durationInWeeks) {
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
                + ", durationInWeeks='" + durationInWeeks + '\''
                + ", active=" + active + '}';
    }
}
