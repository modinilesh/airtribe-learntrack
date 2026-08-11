package entity;

public class Trainer extends Person {

    //It will have methods and parameters inherited from Person class

    @Override
    public String getDisplayName() {
        return "Trainer: " + getFirstName() + " " + getLastName();
    }
}
