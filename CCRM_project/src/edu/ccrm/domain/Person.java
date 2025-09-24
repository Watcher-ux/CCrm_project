package edu.ccrm.domain;

public abstract class Person {
    // 'protected' allows subclasses to access these directly
    protected String id;
    protected String fullName;
    protected String email;

    public Person(String id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    // Abstract method - subclasses MUST provide their own implementation
    public abstract String getDetails();

    // Getters
    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}