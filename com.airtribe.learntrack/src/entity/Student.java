package entity;

public class Student extends Person {

    private String batch;
    private boolean active;

    public Student() {
    }

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
        this.active = true;
    }

    public Student(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
        this.active = true;
    }

    public Student(String firstName, String lastName, String email, String batch) {
        super(firstName, lastName, email);
        this.batch = batch;
        this.active = true;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getDisplayName() {
        return "Student: " + getFirstName() + " " + getLastName();
    }

    @Override
    public String toString() {
        return "Student{id=" + getId()
                + ", firstName='" + getFirstName() + '\''
                + ", lastName='" + getLastName() + '\''
                + ", email='" + getEmail() + '\''
                + ", batch='" + batch + '\''
                + ", active=" + active + '}';
    }
}
