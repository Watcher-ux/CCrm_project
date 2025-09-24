package edu.ccrm.exception;

// This is a custom checked exception.
public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }
}