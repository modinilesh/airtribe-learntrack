package entity;

import java.util.Date;

public class Enrollment {

    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Date enrollmentDate;
    private Status status;

    public Enrollment() {
    }

    public Enrollment(Integer studentId, Integer courseId, Date enrollmentDate) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = Status.ACTIVE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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
