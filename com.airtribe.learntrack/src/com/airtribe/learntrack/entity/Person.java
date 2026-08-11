package com.airtribe.learntrack.entity;

public class Person {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return firstName + " " + lastName;
    }
}
