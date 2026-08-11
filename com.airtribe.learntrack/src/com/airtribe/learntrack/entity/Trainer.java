package com.airtribe.learntrack.entity;

public class Trainer extends Person {

    public Trainer() {
        super();
    }

    public Trainer(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Trainer(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    @Override
    public String getDisplayName() {
        return "Trainer: " + super.getDisplayName();
    }
}
